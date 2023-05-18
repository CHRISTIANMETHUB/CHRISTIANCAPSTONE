package com.example.sport_ticketing.match;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {
        private Long id;
        private Long fieldId;
        private Long tournamentId;
        private List<Integer> teamIds;
        private LocalDateTime dateTime;
}
