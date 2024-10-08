package com.techlearn.Gatekeeper.controller;

import com.techlearn.Gatekeeper.model.Event;
import com.techlearn.Gatekeeper.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event") // Base path for event-related endpoints
public class EventController {

    private final EventService eventService;

    // Constructor-based dependency injection
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/update")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable long id) {
        return eventService.deleteEvent(id);
    }
}
