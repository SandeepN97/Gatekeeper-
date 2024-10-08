package com.techlearn.Gatekeeper.service;

import com.techlearn.Gatekeeper.model.Ticket;
import com.techlearn.Gatekeeper.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public ResponseEntity<List<Ticket>> getAllTickets(){
        try {
            List<Ticket> tickets = ticketRepository.findAll();
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Ticket> getTicketById(long id){
        try {
            Optional<Ticket> ticketOptional = ticketRepository.findById(id);
            if (ticketOptional.isPresent()) {
                Ticket ticket = ticketOptional.get();
                return new ResponseEntity<>(ticket, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Ticket> updateTicket(Ticket newTicket){
        try {
            Optional<Ticket> ticketOptional = ticketRepository.findById(newTicket.getId());
            if (ticketOptional.isPresent()) {
                Ticket oldTicket = ticketOptional.get();
                // Update fields based on the new ticket data
                oldTicket.setUser(newTicket.getUser());
                oldTicket.setEvent(newTicket.getEvent());
                oldTicket.setBookingDate(newTicket.getBookingDate());
                ticketRepository.save(oldTicket);  // Save the updated ticket
                return new ResponseEntity<>(oldTicket, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> createTicket(Ticket ticket){
        try {
            ticketRepository.save(ticket);
            return new ResponseEntity<>("Ticket has been added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add ticket", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteTicket(long id) {
        try {
            Optional<Ticket> ticketOptional = ticketRepository.findById(id);
            if (ticketOptional.isPresent()) {
                Ticket ticketToBeDeleted = ticketOptional.get();
                ticketRepository.delete(ticketToBeDeleted);
                return new ResponseEntity<>("Ticket deleted.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ticket not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete ticket.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
