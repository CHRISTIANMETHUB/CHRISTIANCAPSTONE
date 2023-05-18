package com.example.sport_ticketing.ticket;

import com.example.sport_ticketing.match.Match;
import com.example.sport_ticketing.match.MatchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testSaveTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setCustomerName("John Doe");
        ticket.setTicketPrice(BigDecimal.valueOf(100.00));

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket savedTicket = ticketService.saveTicket(ticket);

        assertEquals(savedTicket.getId(), ticket.getId());
        assertEquals(savedTicket.getCustomerName(), ticket.getCustomerName());
        assertEquals(savedTicket.getTicketPrice(), ticket.getTicketPrice());
    }

    @Test
    public void testGetTicketById() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setCustomerName("John Doe");
        ticket.setTicketPrice(BigDecimal.valueOf(100.00));

        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

        Ticket foundTicket = ticketService.getTicketById(1);

        assertEquals(foundTicket.getId(), ticket.getId());
        assertEquals(foundTicket.getCustomerName(), ticket.getCustomerName());
        assertEquals(foundTicket.getTicketPrice(), ticket.getTicketPrice());
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetTicketById_InvalidId() {
        when(ticketRepository.findById(1)).thenReturn(Optional.empty());

        ticketService.getTicketById(1);
    }

    @Test
    public void testDeleteTicket() {
        doNothing().when(ticketRepository).deleteById(1);

        ticketService.deleteTicket(1);

        verify(ticketRepository, times(1)).deleteById(1);
    }

    @Test
    public void testGetAllTickets() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Ticket> page = new PageImpl<>(Collections.singletonList(new Ticket()));
        when(ticketRepository.findAll(pageable)).thenReturn(page);

        Page<Ticket> foundTickets = ticketService.getAllTickets(pageable);

        assertEquals(foundTickets.getTotalElements(), page.getTotalElements());
    }

    @Test
    public void testToDTO() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setCustomerName("John Doe");
        ticket.setTicketPrice(BigDecimal.valueOf(100.00));

        Match match = new Match();
        match.setMatchId(2L);
        ticket.setMatch(match);

        TicketDto expectedDto = new TicketDto();
        expectedDto.setId(1L);
        expectedDto.setCustomerName("John Doe");
        expectedDto.setMatchId(2L);
        expectedDto.setTicketPrice(BigDecimal.valueOf(100.00));

        TicketDto actualDto = ticketService.toDTO(ticket);

        assertEquals(actualDto.getId(), expectedDto.getId());
        assertEquals(actualDto.getCustomerName(), expectedDto.getCustomerName());
        assertEquals(actualDto.getMatchId(), expectedDto.getMatchId());
        assertEquals(actualDto.getTicketPrice(), expectedDto.getTicketPrice());
    }
}
