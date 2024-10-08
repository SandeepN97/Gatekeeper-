package com.techlearn.Gatekeeper.controller;

import com.techlearn.Gatekeeper.model.Ticket;
import com.techlearn.Gatekeeper.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket") // Base path for ticket-related endpoints
public class TicketController {

    private final TicketService ticketService;

    // Constructor-based dependency injection
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable long id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/update")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable long id) {
        return ticketService.deleteTicket(id);
    }
}
