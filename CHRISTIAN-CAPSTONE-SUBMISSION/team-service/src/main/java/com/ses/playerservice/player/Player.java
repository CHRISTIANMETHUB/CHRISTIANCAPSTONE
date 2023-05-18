package com.ses.playerservice.player;

import com.ses.playerservice.teams.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "country", nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
