/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author MSI
 */
public class Event implements Serializable {

    private String id;
    private String name;
    private int attendees;
    private String location;
    private LocalDate date;
    private Status status;

    public Event(String id, String name, int attendees, String location, LocalDate date, Status status) {
        this.id = id;
        this.name = name;
        this.attendees = attendees;
        this.location = location;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        return id + ";  " + name + ";  " + attendees + ";  " + location + ";  " + date + ";  " + status;
    }
    
    

}
