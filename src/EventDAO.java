
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
public class EventDAO implements IEvent {

    private static final String FILE_NAME = "events.dat";
    
    
     @Override
    public List<Event> loadEvents() {
        List<Event> events = new ArrayList<Event>();
        try(ObjectInputStream ois = new ObjectInputStream (new FileInputStream(FILE_NAME))) {
            events = (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public void saveEvents(List<Event> events) {
       try (ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(FILE_NAME))) {
           oos.writeObject(events);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

   
    @Override
    public void createEvent(Event event) {
        List<Event> events = loadEvents();
        events.add(event);
        saveEvents(events);
    }

    @Override
    public Event searchByID(String id) {
        return loadEvents().stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Event> searchByLocation(String location) {
        List<Event> events = loadEvents();
        List<Event> result = new ArrayList<Event>();
        for (Event event : events) {
            if (event.getLocation().contains(location)) {
                result.add(event);
            }
        }
        return result;

    }

    @Override
    public boolean updateEvent(Event updatedEvent) {
        List<Event> events = loadEvents();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(updatedEvent.getId())) {
                events.set(i, updatedEvent); // Cập nhật sự kiện trong danh sách
                saveEvents(events); // Lưu lại danh sách đã cập nhật xuống file
                return true;
            }
        }
        return false; // Không tìm thấy sự kiện để cập nhật
    }

    @Override
    public boolean deleteEvent(String id) {
        List<Event> events = loadEvents();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(id)) {
                events.remove(i);
                saveEvents(events);
                return true;
            }
        }
        return false;
    }

   

}
