
import java.util.List;
import java.util.Random;

public class EventService {

    private final IEvent iEvent;
    private final Random random = new Random();

    public EventService(IEvent iEvent) {
        this.iEvent = iEvent;
    }

    public void saveToFile() {
        List<Event> events = iEvent.loadEvents();
        iEvent.saveEvents(events);
    }

    List<Event> getAllEvents() {
        return iEvent.loadEvents();
    }

    public boolean checkExistence(String id) {
        return iEvent.searchByID(id) != null;
    }

    public Event searchByID(String id) {
        return iEvent.searchByID(id);
    }

    public List<Event> searchByLocation(String location) {
        return iEvent.searchByLocation(location);
    }

    private String generateID() {
        String id;
        do {
            int num = random.nextInt(9999) + 1; 
            id = String.format("ID%04d", num); 
        } while (iEvent.searchByID(id) != null);
        return id;
    }

    public void createEvent(Event event) {
        event.setId(generateID());
        iEvent.createEvent(event);
        saveToFile();
    }

    public boolean updateEvent(Event updatedEvent) {
        List<Event> events = iEvent.loadEvents();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(updatedEvent.getId())) {
                events.set(i, updatedEvent); // Cập nhật sự kiện trong danh sách
                iEvent.saveEvents(events); // Lưu lại danh sách đã cập nhật xuống file
                return true;
            }
        }
        return false; // Không tìm thấy sự kiện để cập nhật
    }

    public boolean deleteEvent(String id) {
        if (checkExistence(id)) {
            boolean isDeleted = iEvent.deleteEvent(id);
            if (isDeleted) {
                saveToFile();
            }
            return isDeleted;
        } else {
            return false;
        }
    }

}
