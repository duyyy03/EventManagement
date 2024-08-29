
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        IEvent iEvent = new EventDAO();
        EventService eventService = new EventService(iEvent);
        EventManager eventManager = new EventManager(eventService);

        int choice;
        do {
            System.out.println("___________ < EVENT MANAGEMENT SYSTEM > __________");
            System.out.println("|             1. Create Event                    |");
            System.out.println("|             2. Check exist Event               |");
            System.out.println("|             3. Search Event by Location        |");
            System.out.println("|             4. Update Event                    |");
            System.out.println("|             5. Delete Event                    |");
            System.out.println("|             6. Save Events to File             |");
            System.out.println("|             7. Display All Events              |");
            System.out.println("|             8. Exit                            |");
            System.out.println("__________________________________________________");
            System.out.print(" -> Enter your choice: ");
            
            try {
                choice = Integer.parseInt(sc.nextLine());

                boolean success = false;
                switch (choice) {
                    case 1:
                        eventManager.createEvent();
                        success = true;
                        break;
                    case 2:
                        eventManager.checkExistence();
                        success = true;
                        break;
                    case 3:
                        eventManager.searchByLocation();
                        success = true;
                        break;
                    case 4:
                        eventManager.updateEvent();
                        success = true;
                        break;
                    case 5:
                        eventManager.deleteEvent();
                        success = true;
                        break;
                    case 6:
                        eventManager.saveEventsToFile();
                        success = true;
                        break;
                    case 7:
                        eventManager.displayAllEvents();
                        success = true;
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println(" -> Invalid choice. Please try again. ");
                }

                if (success) {
                    if (!promptReturnToMenu()) {
                        choice = 8;  // Exit if user does not want to go back to menu
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(" -> Invalid input. Please enter again. ");
                choice = -1;  // Set to an invalid choice to continue the loop
            }
        } while (choice != 8);
    }

    private static boolean promptReturnToMenu() {
        while (true) {
            System.out.print(" -> Do you want go back to main menu (Y/N): ");
            String backToMenu = sc.nextLine().trim().toLowerCase();
            if (backToMenu.equals("y")) {
                return true;
            } else if (backToMenu.equals("n")) {
                return false;
            } else {
                System.out.print(" -> Invalid input. Please enter 'Y' 'N' : ");
            }
        }
    }
}
