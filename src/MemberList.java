import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Calendar;

public class MemberList {
    private static final int NOT_FOUND = -1;
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH = 4;
    private Member[] members;
    private int size;

    public MemberList() {
        members = new Member[INITIAL_CAPACITY];
        size = 0;
    }

    public int find(Member member) {
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
        System.out.println("-list of members loaded-");

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data.trim().isEmpty()) {
                continue;
            }
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
                throw new IllegalArgumentException(tokens[5] + ": Invalid studio location!");
            }

            Profile profile = new Profile(tokens[1], tokens[2], birthDate);
            Member newMember;

            if (tokens[0].equalsIgnoreCase("B")) {
                newMember = new Basic(profile, expirationDate, location);
                add(newMember);
            } else if (tokens[0].equalsIgnoreCase("F")) {
                newMember = new Family(profile, expirationDate, location);
                if (expirationDate.isExpired()) { // Check if member is expired or not
                    ((Family) newMember).setGuest(false);
                }
                add(newMember);
            } else {
                newMember = new Premium(profile, expirationDate, location);
                if (expirationDate.isExpired()) { // Check if member is expired or not
                    ((Premium) newMember).setGuestPass(0);
                }
                add(newMember);
            }

            System.out.println(newMember.toString());

        }

        System.out.println("-end of list-");
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
            for (Member member : members) {
                if (member != null) {
                    System.out.println(member);
                }
            }
        }

    }

    public void printByMember(boolean sCommand) { // We need this sCommand boolean otherwise we need one more method
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
            for (Member member : members) {
                if (member != null) {
                    if (sCommand) { // Please keep this line as we need to indent each line in the result for "S" command.
                        System.out.print("   ");
                    }
                    System.out.println(member);
                }
            }
        }
    }

    public void printFees() {
        String todayDate = getTodayDate();
        Date today = stringToDate(todayDate);

        for (Member member : members) {

            if (member != null) {
                boolean isExpired = expired(member, today);

                if (member instanceof Basic) {
                    System.out.println(member + " [next due: $" + member.bill() + "]");
                }
                if (member instanceof Family) {
                    if (isExpired) {
                        System.out.println(member + " [next due: $" + member.bill() + "]");
                    } else {
                        if (((Family) member).getGuest()) {
                            System.out.println(member + " [next due: $" + member.bill() + "]");
                        } else {
                            System.out.println(member + " [next due: $" + member.bill() + "]");
                        }
                    }
                }
                if (member instanceof Premium) {
                    if (isExpired) {
                        System.out.println(member + " [next due: $" + member.bill() + "]");
                    } else {
                        System.out.println(member + " [next due: $" + member.bill() + "]");
                    }
                }
            }
        }
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

    public Member getMember(int i) {
        return members[i];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
