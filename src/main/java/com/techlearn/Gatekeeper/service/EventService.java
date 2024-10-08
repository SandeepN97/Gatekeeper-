package com.techlearn.Gatekeeper.service;

import com.techlearn.Gatekeeper.model.Event;
import com.techlearn.Gatekeeper.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public ResponseEntity<List<Event>> getAllEvents() {
        try {
            List<Event> events = eventRepository.findAll();
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Event> getEventById(long id) {
        try {
            Optional<Event> eventOptional = eventRepository.findById(id);
            if (eventOptional.isPresent()) {
                Event event = eventOptional.get();
                return new ResponseEntity<>(event, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Event> updateEvent(Event newEvent) {
        try {
            Optional<Event> eventOptional = eventRepository.findById(newEvent.getId());
            if (eventOptional.isPresent()) {
                Event oldEvent = eventOptional.get();
                // Update fields based on the new event data
                oldEvent.setName(newEvent.getName());
                oldEvent.setLocation(newEvent.getLocation());
                oldEvent.setDate(newEvent.getDate());
                oldEvent.setAvailableSeats(newEvent.getAvailableSeats());
                eventRepository.save(oldEvent);  // Save the updated event
                return new ResponseEntity<>(oldEvent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> createEvent(Event event) {
        try {
            eventRepository.save(event);
            return new ResponseEntity<>("Event has been created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create event", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteEvent(long id) {
        try {
            Optional<Event> eventOptional = eventRepository.findById(id);
            if (eventOptional.isPresent()) {
                Event eventToBeDeleted = eventOptional.get();
                eventRepository.delete(eventToBeDeleted);
                return new ResponseEntity<>("Event deleted.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Event not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete event.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
