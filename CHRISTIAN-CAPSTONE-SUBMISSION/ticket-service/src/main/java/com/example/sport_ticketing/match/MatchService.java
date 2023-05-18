package com.example.sport_ticketing.match;

import com.example.sport_ticketing.field.Field;
import com.example.sport_ticketing.field.FieldRepository;
import com.example.sport_ticketing.tournament.Tournament;
import com.example.sport_ticketing.tournament.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Match not found"));
    }

    public Page<Match> getAllMatches(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }

    public void deleteMatchById(Long id) {
        matchRepository.deleteById(id);
    }


    public  MatchDto toDto(Match match) {
        MatchDto dto = new MatchDto();
        dto.setId(match.getMatchId());
        dto.setFieldId(match.getField().getFieldId());
        dto.setTournamentId(match.getTournament().getTournamentId());

        List<Integer> integerList = Arrays.stream(match.getTeamIds().split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        dto.setTeamIds(integerList);
        dto.setDateTime(match.getDateTime());
        return dto;
    }

    public  Match toMatch(MatchDto dto) {

        Match match = new Match();
        Optional<Field> field = fieldRepository.findById(dto.getFieldId());
        if(field.isEmpty()){
            throw new NoSuchElementException("Field with id " + dto.getFieldId() + " does not exists");
        }
        Optional<Tournament> tournament = tournamentRepository.findById(dto.getTournamentId());
        if(tournament.isPresent()){

            match.setTournament(tournament.get());

        }else {
            throw new NoSuchElementException("Tournament with id " + dto.getTournamentId() + " does not exists");
        }

        match.setMatchId(dto.getId());
        match.setField(field.get());
        StringJoiner joiner = new StringJoiner(",");
        for (Integer i : dto.getTeamIds()) {
            joiner.add(i.toString());
        }
        match.setTeamIds(joiner.toString());
        match.setDateTime(dto.getDateTime());
        return match;
    }
}