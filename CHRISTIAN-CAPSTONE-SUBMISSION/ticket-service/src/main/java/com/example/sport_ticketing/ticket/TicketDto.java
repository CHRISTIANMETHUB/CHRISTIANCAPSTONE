package com.example.sport_ticketing.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private Long id;
    private String customerName;
    private Long matchId;
    private BigDecimal ticketPrice;

}