import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Scanner;

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

    }

    public void printByMember() {

    }

    public void printFees() {

    }
}
