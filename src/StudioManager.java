import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StudioManager {
    private MemberList memberList;
    public StudioManager() {
        this.memberList = new MemberList();
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Studio Manager is up running...");

        File file = new File("memberList.txt");
        try {
            memberList.load(file);
        } catch (IOException e) {
            System.out.println("Error loading memberList.txt " + e.getMessage());
        }

        while (true) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");

            switch (tokens[0]) {
                case "AB": // add a member with Basic membership

                    break;
                case "AF": // add a member with Family membership to the member database

                    break;
                case "AP": // add a member with Premium membership to the member database

                    break;
                case "C": // cancel the membership and remove the member from the member database

                    break;
                case "S": // display the class schedule with the current attendees

                    break;
                case "PM": // display the members sorted by member profiles

                    break;
                case "PC": // display the members sorted by county names

                    break;
                case "PF": // print the members with the membership fees

                    break;
                case "R": // take attendance of a member attending a class and add the member to the class

                    break;
                case "U": // remove a member from a class

                    break;
                case "RG": // take attendance of a guest attending a class and add the guest to the class

                    break;
                case "UG": // remove the guest from a class

                    break;
                case "Q":  // quit
                    System.out.println("Studio Manager terminated.");
                    return;
                case "": // empty line
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    private void addMember(String fname, String lname, Date dob, Location homeStudio) {
        Profile profile = new Profile(fname, lname, dob);

    }

}
