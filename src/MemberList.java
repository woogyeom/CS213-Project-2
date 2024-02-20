import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.Calendar;

public class MemberList {
    private static final int NOT_FOUND = -1;
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH = 4;
    private Member[] members;
    private int size;

    private int find(Member member) {
        for (int i = 0; i < size; i++) {
            if (members[i].getProfile().compareTo(member.getProfile()) == 0) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        Member[] newMembers = new Member[members.length + GROWTH];
        for (int i = 0; i < size; i++) {
            newMembers[i] = members[i];
        }
        members = newMembers;
    }

    public boolean contains(Member member) {
        return find(member) != NOT_FOUND;
    }

    public boolean add(Member member) {
        if (contains(member)) {
            return false;
        } //members list already contains this member
        if (size == members.length) {
            grow();
        } //if members is full, increase it's size

        members[size] = member;
        size++;
        return true;
    }

    public boolean remove(Member member) {
        int index = find(member);
        if (index == NOT_FOUND) {
            return false;
        } // return false if member is not in the member list

        for (int i = index; i < size-1; i++) {
            members[i] = members[i+1];
        }
        size--;
        members[size] = null;
        return true;
    }

    public void load(File file) throws IOException {
        //file = new File("memberList.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] tokens = data.split(" +");

            if (tokens.length != 6) {
                throw new IllegalArgumentException("Expected 6 tokens, but found " + tokens.length);
            }
            if (!(tokens[0].equalsIgnoreCase("B")) && !(tokens[0].equalsIgnoreCase("F")) && !(tokens[0].equalsIgnoreCase("P"))) {
                throw new IllegalArgumentException("Invalid membership type. Expected B, F or P but got: "+ tokens[0]);
            }

            Date birthDate = stringToDate(tokens[3]);
            Date expirationDate = stringToDate(tokens[4]);
            Location location;

            if (tokens[5].equalsIgnoreCase("Bridgewater")) {
                location = Location.BRIDGEWATER;
            } else if (tokens[5].equalsIgnoreCase("Edison")) {
                location = Location.EDISON;
            } else if (tokens[5].equalsIgnoreCase("Franklin")) {
                location = Location.FRANKLIN;
            } else if (tokens[5].equalsIgnoreCase("Piscataway")) {
                location = Location.PISCATAWAY;
            } else if (tokens[5].equalsIgnoreCase("Somerville")) {
                location = Location.SOMERVILLE;
            } else {
                throw new IllegalArgumentException("Invalid location token: "+ tokens[5]);
            }

            Profile profile = new Profile(tokens[1], tokens[2], birthDate);

            if (tokens[0].equalsIgnoreCase("B")) {
                Basic newMember = new Basic(profile, expirationDate, location);
                add(newMember);
            } else if (tokens[0].equalsIgnoreCase("F")) {
                Family newMember = new Family(profile, expirationDate, location);
                add(newMember);
            } else {
                Premium newMember = new Premium(profile,expirationDate, location, 3);
                add(newMember);
            }

        }
    }

    private Date stringToDate(String string) throws IllegalArgumentException {
        String[] tokens = string.split("/");

        int year = Integer.parseInt(tokens[2]);
        int month = Integer.parseInt(tokens[0]);
        int day = Integer.parseInt(tokens[1]);

        return new Date(month, day, year);
    }

    public void printByCounty() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (members[j].getHomeStudio().getCounty().compareTo(members[minIndex].getHomeStudio().getCounty()) < 0) {
                    minIndex = j;
                } else if (members[j].getHomeStudio().getCounty().compareTo(members[minIndex].getHomeStudio().getCounty()) == 0) {
                    if (members[j].getHomeStudio().getZipcode().compareTo(members[minIndex].getHomeStudio().getZipcode()) < 0) {
                        minIndex = j;
                    }
                }
            }

            Member temp = members[minIndex];
            members[minIndex] = members[i];
            members[i] = temp;
        }

        if (size == 0) {
            System.out.println("Members List is empty!");
        } else {
            System.out.println("-list of members sorted by county-");
            for (Member member : members) {
                if (member != null) {
                    System.out.println(member);
                }
            }
            System.out.println("-end of list-");
        }

    }

    public void printByMember() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (members[j].getProfile().compareTo(members[minIndex].getProfile()) < 0) {
                    minIndex = j;
                }
            }

            Member temp = members[minIndex];
            members[minIndex] = members[i];
            members[i] = temp;
        }

        if (size == 0) {
            System.out.println("Members List is empty!");
        } else {
            System.out.println("-list of members sorted by member profiles-");
            for (Member member : members) {
                if (member != null) {
                    System.out.println(member);
                }
            }
            System.out.println("-end of list-");
        }
    }

    public void printFees() {
        String todayDate = getTodayDate();
        Date today = stringToDate(todayDate);

        for (Member member : members) {
            boolean isExpired = expired(member, today);

            if (member instanceof Basic) {
                System.out.println(member + ", (Basic) number of classes attended: " + ((Basic) member).getNumClases() + " [next due: $" + member.bill() + "]");
            }
            if (member instanceof Family) {
                if (isExpired) {
                    System.out.println(member + ", (Family) guest-pass remaining: not eligible [next due: $" + member.bill() + "]");
                } else {
                    if (((Family) member).getGuest()) {
                        System.out.println(member + ", (Family) guest-pass remaining: 1 [next due: $" + member.bill() + "]");
                    } else {
                        System.out.println(member + ", (Family) guest-pass remaining: 0 [next due: $" + member.bill() + "]");
                    }
                }
            }
            if (member instanceof Premium) {
                if (isExpired) {
                    System.out.println(member + ", (Premium) guest-pass remaining: not eligible [next due: $" + member.bill() + "]");
                } else {
                    System.out.println(member + ", (Premium) guest-pass remaining: " + ((Premium) member).getGuestPass() + " [next due: $" + member.bill() + "]");
                }
            }
        }
        System.out.println("-end of list-");
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return (month+1) + "/" + day + "/" + year;
    }

    private boolean expired(Member member, Date today) {
        return member.getExpire().compareTo(today) < 0;
    }
}
