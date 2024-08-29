
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
public interface IEvent {

    List<Event> loadEvents();

    void saveEvents(List<Event> events);

    void createEvent(Event event);

    Event searchByID(String id);

    List<Event> searchByLocation(String location);

    boolean updateEvent(Event updateEvent);

    boolean deleteEvent(String id);

}
