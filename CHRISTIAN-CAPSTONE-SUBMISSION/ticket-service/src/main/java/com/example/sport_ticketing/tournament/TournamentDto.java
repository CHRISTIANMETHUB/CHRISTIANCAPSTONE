package com.example.sport_ticketing.tournament;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDto {

    private Long tournamentId;
    private String name;
    private String category;
    private String style;
}