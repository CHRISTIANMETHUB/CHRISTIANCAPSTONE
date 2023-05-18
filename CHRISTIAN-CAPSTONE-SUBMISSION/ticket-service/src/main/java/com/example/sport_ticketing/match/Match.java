package com.example.sport_ticketing.match;

import com.example.sport_ticketing.field.Field;
import com.example.sport_ticketing.tournament.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "match")
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="match_id")
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;


    @Column(name= "teams_id", nullable = false)
    private String teamIds;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

}