package com.example.sport_ticketing.ticket;


import com.example.sport_ticketing.match.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Column(name = "ticket_price", nullable = false)
    private BigDecimal ticketPrice;

}
