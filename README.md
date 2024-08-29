# Event Management

## Overview
The Event Management system is designed for a local event organizing company to efficiently manage events, including creating, updating, and storing events in a binary file. The system leverages object-oriented principles such as abstraction, polymorphism, encapsulation, and inheritance.

## Features

### Core Functions
1. **Create an Event**: Input details like event name, date, location, number of attendees, and status. Automatically generate a unique event ID.
2. **Check Event Existence**: Verify if an event exists in the system by its ID.
3. **Search Events by Location**: Search for events based on location and display them by attendance.
4. **Update or Delete Events**:
   - Update event details by event ID.
   - Delete events after confirmation.
5. **Save Events to File**: Persist events in a binary file (`events.dat`).
6. **Print Events from File**: Display all events stored in the file, sorted by date and name.
7. **Exit**: Quit the program safely.

## Object-Oriented Design
- **Data Structure**: Utilizes `ArrayList` for event storage.
- **Design Patterns**: Optionally implement patterns like DAO, Factory, or Repository for additional credits.

## Usage
Each menu option corresponds to a specific function. The menu reappears after each operation until the user chooses to exit.




