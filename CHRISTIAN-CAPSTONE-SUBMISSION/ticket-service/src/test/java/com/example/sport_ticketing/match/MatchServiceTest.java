package com.example.sport_ticketing.match;

import com.example.sport_ticketing.field.Field;
import com.example.sport_ticketing.field.FieldRepository;
import com.example.sport_ticketing.tournament.Tournament;
import com.example.sport_ticketing.tournament.TournamentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private TournamentRepository tournamentRepository;

    @InjectMocks
    private MatchService matchService;
    Match match;
    MatchDto matchDto;

    @Before
    public void init() {
        match = new Match();
        match.setMatchId(1L);
        match.setDateTime(LocalDateTime.now());
        Field field = new Field();
        field.setFieldId(1L);
        match.setField(field);
        Tournament tournament = new Tournament();
        tournament.setTournamentId(1L);
        match.setTournament(tournament);
        match.setTeamIds("1,2");

        matchDto = new MatchDto();
        matchDto.setId(1L);
        matchDto.setDateTime(LocalDateTime.now());
        matchDto.setFieldId(1L);
        matchDto.setTournamentId(1L);
        List<Integer> teamIds = Arrays.asList(1, 2);
        matchDto.setTeamIds(teamIds);
    }
    @Test
    public void testSaveMatch() {
        // create a new match
        Match match = new Match();
        match.setMatchId(1L);
        match.setField(new Field());
        match.setTournament(new Tournament());
        match.setTeamIds("1,2,3");
        match.setDateTime(LocalDateTime.now());

        // mock the save method of the match repository
        when(matchRepository.save(any(Match.class))).thenReturn(match);

        // save the match
        Match savedMatch = matchService.saveMatch(match);

        // verify that the match was saved
        verify(matchRepository).save(match);
        assertEquals(match, savedMatch);
    }

    @Test
    public void testGetMatchById() {
        // create a match with id 1
        Match match = new Match();
        match.setMatchId(1L);
        match.setField(new Field());
        match.setTournament(new Tournament());
        match.setTeamIds("1,2,3");
        match.setDateTime(LocalDateTime.now());

        // mock the findById method of the match repository
        when(matchRepository.findById(1L)).thenReturn(Optional.of(match));

        // get the match by id
        Match foundMatch = matchService.getMatchById(1L);

        // verify that the match was found
        verify(matchRepository).findById(1L);
        assertEquals(match, foundMatch);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetMatchByIdNotFound() {
        // mock the findById method of the match repository to return an empty optional
        when(matchRepository.findById(1L)).thenReturn(Optional.empty());

        // try to get the match by id that does not exist
        matchService.getMatchById(1L);
    }

    @Test
    public void testGetAllMatches() {
        // create some matches
        Match match1 = new Match();
        match1.setMatchId(1L);
        match1.setField(new Field());
        match1.setTournament(new Tournament());
        match1.setTeamIds("1,2,3");
        match1.setDateTime(LocalDateTime.now());

        Match match2 = new Match();
        match2.setMatchId(2L);
        match2.setField(new Field());
        match2.setTournament(new Tournament());
        match2.setTeamIds("4,5,6");
        match2.setDateTime(LocalDateTime.now());

        List<Match> matches = Arrays.asList(match1, match2);

        // mock the findAll method of the match repository
        Page<Match> page = new PageImpl<>(matches);
        when(matchRepository.findAll(any(Pageable.class))).thenReturn(page);

        // get all the matches
        Page<Match> foundMatches = matchService.getAllMatches(PageRequest.of(0, 10));

        // verify that the matches were found
        verify(matchRepository).findAll(any(Pageable.class));
        assertEquals(matches, foundMatches.getContent());
    }

    @Test
    public void testDeleteMatchById() {
        // delete a match by id
        matchService.deleteMatchById(1L);

        // verify that the deleteById method of the match repository was called
        verify(matchRepository).deleteById(1L);
    }

    @Test
    public void testToDto() {
        MatchDto matchDto = matchService.toDto(match);

// verify matchDto values
        assertEquals(match.getMatchId(), matchDto.getId());
        assertEquals(match.getDateTime(), matchDto.getDateTime());
        assertEquals(match.getField().getFieldId(), matchDto.getFieldId());
        assertEquals(match.getTournament().getTournamentId(), matchDto.getTournamentId());
        List<Integer> teamIds = Arrays.asList(1, 2);
        assertEquals(teamIds, matchDto.getTeamIds());
    }

    @Test
    public void testToMatch() {
        Field field = new Field();
        field.setFieldId(1L);
        Tournament tournament = new Tournament();
        tournament.setTournamentId(1L);

// mock fieldRepository and tournamentRepository
        when(fieldRepository.findById(matchDto.getFieldId())).thenReturn(Optional.of(field));
        when(tournamentRepository.findById(matchDto.getTournamentId())).thenReturn(Optional.of(tournament));

// create a match
        Match match = matchService.toMatch(matchDto);

// verify match values
        assertEquals(matchDto.getId(), match.getMatchId());
        assertEquals(matchDto.getDateTime(), match.getDateTime());
        assertEquals(field, match.getField());
        assertEquals(tournament, match.getTournament());
        assertEquals("1,2", match.getTeamIds());
    }
    @Test(expected = NoSuchElementException.class)
    public void testToMatchWithNonExistingField() {
        // create a MatchDto with a non-existing Field id
        MatchDto dto = new MatchDto();
        dto.setId(1L);
        dto.setFieldId(999L); // non-existing id
        dto.setTournamentId(1L);
        dto.setTeamIds(Arrays.asList(1, 2));
        dto.setDateTime(LocalDateTime.now());

        // call toMatch() which should throw a NoSuchElementException
        matchService.toMatch(dto);
    }

}
