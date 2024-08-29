
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
/**
 *
 * @author MSI
 */
public class EventManager {

    private final EventService eventService;

    public EventManager(EventService eventService) {
        this.eventService = eventService;
    }

    public void saveEventsToFile() {
        eventService.saveToFile();
        System.out.println(" [ Event have been successfully saved to the file ] ");

    }

    public void checkExistence() {
        String idPattern = "ID[0-9]{4}";
        String id = MyTool.readPattern("Enter event's ID to check: ", idPattern);

        Event event = eventService.searchByID(id);
        if (event == null) {
            System.out.println(" [ Event does not exist! ] ");
        } else {
            System.out.println(" [ Event exists! ] ");
        }
    }

    public void searchByLocation() {
        String location = MyTool.readNonBlank("Enter location to search: ");
        List<Event> events = eventService.searchByLocation(location);
        if (events.isEmpty()) {
            System.out.println(" [ No event found ] ");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    public void displayAllEvents() {
        List<Event> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            System.out.println(" [ There are no events to display ] ");
            return;
        }
        events.sort(Comparator.comparing(Event::getDate).thenComparing(Event::getName));
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public void createEvent() {
        String name = MyTool.readValidEventName("Enter event name (at least 5 characters, no spaces): ");
        LocalDate date = MyTool.readValidDateNotEmpty("Enter event date (YYYY-MM-DD): ", "yyyy-MM-dd");
        String location = MyTool.readNonBlank("Enter location: ");
        int attendees = MyTool.readInt("Enter number of attendees: ");
        Status status = MyTool.readStatus("Enter status (1 for Available; 0 for Not Available): ");
        Event event = new Event("", name, attendees, location, date, status);
        eventService.createEvent(event);
        System.out.println("Event is created successfully.[ Event ID is: " + event.getId() + "]");
    }

    public void updateEvent() {
        String id = MyTool.readPattern("Enter event ID to update: ", "ID[0-9]{4}");
        if ("ID0000".equals(id)) {
            System.out.println(" [ Invalid ID format ] ");
            return;
        }
        Event event = eventService.searchByID(id);
        if (event == null) {
            System.out.println(" [ Event does not exist ] ");
            return;
        }
        String name = MyTool.readOptionalAlphaString("Enter new event name (leave blank to keep current): ");
        MyTool.updateIfNotNullOrEmpty(name, event::setName);

        LocalDate date = MyTool.readValidDate("Enter new event date (YYYY-MM-DD, leave blank to keep current): ", "yyyy-MM-dd", true);
        MyTool.updateIfNotNullOrEmpty(date, event::setDate);

        String location = MyTool.readOptionalAlphaString("Enter new event location (leave blank to keep current): ");
        MyTool.updateIfNotNullOrEmpty(location, event::setLocation);

        Integer attendees = MyTool.readOptionalInt("Enter new number of attendees (leave blank to keep current): ");
        MyTool.updateIfNotNullOrEmpty(attendees, event::setAttendees);

        Status status = MyTool.readOptionlStatus("Enter new status (1 for Available, 0 for Not Available, leave blank to keep current): ");
        if (status != null) {
            event.setStatus(status);
        }

        boolean isUpdated = eventService.updateEvent(event);
        System.out.println(isUpdated ? " [ Event updated successfully ] " : " [ Event update failed ]");
    }

    public void deleteEvent() {
        String id = MyTool.readPattern("Enter event ID to delete: ", "ID[0-9]{4}");
        if ("ID0000".equals(id)) {
            System.out.println(" [ Invalid ID format ] ");
            return;
        }
        Event event = eventService.searchByID(id);
        if (event == null) {
            System.out.println(" [ Event does not exist ] ");
            return;
        }
        boolean isDelete = eventService.deleteEvent(id);
        System.out.println(isDelete ? " [ Event is deleted successfully ] " : " [ Event deletion failed ] ");
    }

}
