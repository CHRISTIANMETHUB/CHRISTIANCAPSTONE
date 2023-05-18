package com.example.sport_ticketing.match;

import com.example.sport_ticketing.field.Field;
import com.example.sport_ticketing.tournament.Tournament;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MatchControllerTest {

    @Mock
    private MatchService matchService;

    @InjectMocks
    private MatchController matchController;

    @Test
    public void testGetMatchById() {
        // create a match
        Match match = new Match();
        match.setMatchId(1L);
        when(matchService.getMatchById(anyLong())).thenReturn(match);

        // make the request
        ResponseEntity<Match> response = matchController.getMatchById(1L);

        // verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(match, response.getBody());
        verify(matchService, times(1)).getMatchById(1L);
    }

    @Test
    public void testGetAllMatches() {
        // create page
        List<Match> matches = Arrays.asList(new Match(), new Match());
        Page<Match> page = new PageImpl<>(matches);
        when(matchService.getAllMatches(any())).thenReturn(page);

        // make the request
        ResponseEntity<Page<Match>> response = matchController.getAllMatches(PageRequest.of(0, 10));

        // verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
        verify(matchService, times(1)).getAllMatches(any());
    }

    @Test
    public void testCreateMatch() {
        // create a match dto
        MatchDto matchDto = new MatchDto();
        matchDto.setId(1L);
        matchDto.setFieldId(1L);
        matchDto.setTournamentId(1L);
        matchDto.setTeamIds(Arrays.asList(1, 2));
        matchDto.setDateTime(LocalDateTime.now());

        // create a match
        Match match = new Match();
        match.setMatchId(1L);
        when(matchService.toMatch(any(MatchDto.class))).thenReturn(match);
        when(matchService.saveMatch(any(Match.class))).thenReturn(match);

        // make the request
        ResponseEntity<Match> response = matchController.createMatch(matchDto);

        // verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(match, response.getBody());
        verify(matchService, times(1)).toMatch(matchDto);
        verify(matchService, times(1)).saveMatch(match);
    }

    @Test
    public void testUpdateMatch() throws Exception {
        // create a test MatchDto
        MatchDto matchDto = new MatchDto();
        matchDto.setId(1L);
        matchDto.setFieldId(2L);
        matchDto.setTournamentId(3L);
        matchDto.setTeamIds(Arrays.asList(4, 5));
        matchDto.setDateTime(LocalDateTime.of(2022, 1, 1, 12, 0));
        Field field = mock(Field.class);
        Tournament tournament = mock(Tournament.class);
        // create a mock Match object for the service to return
        Match match = new Match();
        match.setMatchId(1L);
        match.setField(field);
        match.setTournament(tournament);
        match.setTeamIds("4,5");
        match.setDateTime(LocalDateTime.of(2022, 1, 1, 12, 0));

        when(matchService.toMatch(matchDto)).thenReturn(match);
        when(matchService.saveMatch(match)).thenReturn(match);

        // make the request
        ResponseEntity<Match> response = matchController.updateMatch(1L, matchDto);

        // check the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(match, response.getBody());
    }

    @Test
    public void testDeleteMatch() {
        // given
        Long matchId = 1L;

        // when
        ResponseEntity<Void> responseEntity = matchController.deleteMatch(matchId);

        // then
        verify(matchService).deleteMatchById(matchId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteMatchWithNonExistingId() {
        // given
        Long matchId = 1L;
        doThrow(new NoSuchElementException("Match not found")).when(matchService).deleteMatchById(matchId);

        // when
        try {
            matchController.deleteMatch(matchId);
        } catch (NoSuchElementException e) {
            // then
            assertEquals("Match not found", e.getMessage());
        }
    }
}
