package com.example.sport_ticketing.match;

import com.example.sport_ticketing.field.Field;
import com.example.sport_ticketing.tournament.Tournament;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MatchTest {

    @Mock
    private Field field;

    @Mock
    private Tournament tournament;

    @Test
    public void testMatchConstructor() {
        Long id = 1L;
        String teamIds = "1,2,3";
        LocalDateTime dateTime = LocalDateTime.of(2023, 5, 12, 13, 0, 0);
        Match match = new Match(id, field, tournament, teamIds, dateTime);

        assertEquals(id, match.getMatchId());
        assertEquals(field, match.getField());
        assertEquals(tournament, match.getTournament());
        assertEquals(teamIds, match.getTeamIds());
        assertEquals(dateTime, match.getDateTime());
    }

    @Test
    public void testMatchSettersAndGetters() {
        Match match = new Match();

        Long id = 1L;
        String teamIds = "1,2,3";
        LocalDateTime dateTime = LocalDateTime.of(2023, 5, 12, 13, 0, 0);

        match.setMatchId(id);
        match.setField(field);
        match.setTournament(tournament);
        match.setTeamIds(teamIds);
        match.setDateTime(dateTime);

        assertEquals(id, match.getMatchId());
        assertEquals(field, match.getField());
        assertEquals(tournament, match.getTournament());
        assertEquals(teamIds, match.getTeamIds());
        assertEquals(dateTime, match.getDateTime());
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Long id = 1L;
        Long fieldId = 2L;
        Long tournamentId = 3L;
        List<Integer> teamIds = Arrays.asList(1, 2, 3);
        LocalDateTime dateTime = LocalDateTime.now();

        MatchDto matchDto = new MatchDto();

        // Act
        matchDto.setId(id);
        matchDto.setFieldId(fieldId);
        matchDto.setTournamentId(tournamentId);
        matchDto.setTeamIds(teamIds);
        matchDto.setDateTime(dateTime);

        // Assert
        assertEquals(id, matchDto.getId());
        assertEquals(fieldId, matchDto.getFieldId());
        assertEquals(tournamentId, matchDto.getTournamentId());
        assertEquals(teamIds, matchDto.getTeamIds());
        assertEquals(dateTime, matchDto.getDateTime());
    }

    @Test
    public void testConstructor() {
        // Arrange
        Long id = 1L;
        Long fieldId = 2L;
        Long tournamentId = 3L;
        List<Integer> teamIds = Arrays.asList(1, 2, 3);
        LocalDateTime dateTime = LocalDateTime.now();

        // Act
        MatchDto matchDto = new MatchDto(id, fieldId, tournamentId, teamIds, dateTime);

        // Assert
        assertEquals(id, matchDto.getId());
        assertEquals(fieldId, matchDto.getFieldId());
        assertEquals(tournamentId, matchDto.getTournamentId());
        assertEquals(teamIds, matchDto.getTeamIds());
        assertEquals(dateTime, matchDto.getDateTime());
    }

}
