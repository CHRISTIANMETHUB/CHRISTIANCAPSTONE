package com.example.sport_ticketing.ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDto ticket) {
        Ticket savedTicket = ticketService.saveTicket(ticketService.toEntity(ticket));
        return ResponseEntity.ok(savedTicket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Integer id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Ticket>> getAllTicketsPaged(Pageable pageable) {
        Page<Ticket> tickets = ticketService.getAllTicketsPaged(pageable);
        return ResponseEntity.ok(tickets);
    }
}
