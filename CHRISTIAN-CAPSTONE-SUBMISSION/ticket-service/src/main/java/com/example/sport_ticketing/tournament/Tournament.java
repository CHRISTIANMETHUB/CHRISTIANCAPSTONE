package com.example.sport_ticketing.tournament;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tournament")
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private Long tournamentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "style", nullable = false)
    private String style;
}
