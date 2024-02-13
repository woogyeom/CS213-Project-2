import java.util.Scanner;

public class StudioManager {
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Studio Manager is up running...");
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
}
