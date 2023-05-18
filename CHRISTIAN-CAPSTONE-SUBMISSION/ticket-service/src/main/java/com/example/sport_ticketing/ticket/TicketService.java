package com.example.sport_ticketing.ticket;

import com.example.sport_ticketing.match.Match;
import com.example.sport_ticketing.match.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MatchRepository matchRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }

    public Page<Ticket> getAllTickets(Pageable page) {
        return ticketRepository.findAll(page);
    }

    public Page<Ticket> getAllTicketsPaged(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    public TicketDto toDTO(Ticket ticket) {
        TicketDto ticketDTO = new TicketDto();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setCustomerName(ticket.getCustomerName());
        ticketDTO.setMatchId(ticket.getMatch().getMatchId());
        ticketDTO.setTicketPrice(ticket.getTicketPrice());
        return ticketDTO;
    }

    public Ticket toEntity(TicketDto ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setCustomerName(ticketDTO.getCustomerName());
        Match match = matchRepository.findById(ticketDTO.getMatchId())
                .orElseThrow(() -> new NoSuchElementException("Match with id " + ticketDTO.getMatchId() + " not found"));
        ticket.setMatch(match);
        ticket.setTicketPrice(ticketDTO.getTicketPrice());
        return ticket;
    }
}