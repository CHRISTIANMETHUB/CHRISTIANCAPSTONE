package com.example.sport_ticketing.tournament;

import com.example.sport_ticketing.exception.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TournamentService {

    private static final String NO_TOURNAMENT_FOUND = "No tournament found";
    @Autowired
    private TournamentRepository tournamentRepository;

    public Tournament save(Tournament tournament) {

        if(tournament.getTournamentId() != null) {
            Optional<Tournament> tour = tournamentRepository.findById(tournament.getTournamentId());
            if(tour.isPresent()){
                throw new DuplicateEntityException("Tournament with id "+ tournament.getTournamentId() + " already exists");
            }
        }
        return tournamentRepository.save(tournament);
    }

    public Page<Tournament> findAll(Pageable page) {
        return tournamentRepository.findAll(page);
    }

    public Tournament findById(Long id) {
        Optional<Tournament> tournament = tournamentRepository.findById(id);

        if(tournament.isPresent()){
            return tournament.get();
        }
        throw new NoSuchElementException(NO_TOURNAMENT_FOUND);
    }

    public void deleteById(Long id) {
        Optional<Tournament> tournament = tournamentRepository.findById(id);

        if(tournament.isPresent()){
            tournamentRepository.deleteById(id);
        } else {
            throw new NoSuchElementException(NO_TOURNAMENT_FOUND);
        }

    }

    public Tournament updateTournament(Tournament newTournament){
        Optional<Tournament> tournament = tournamentRepository.findById(newTournament.getTournamentId());

        if(tournament.isPresent()){
            return tournamentRepository.save(newTournament);
        } else {
            throw new NoSuchElementException(NO_TOURNAMENT_FOUND);
        }
    }

    public TournamentDto toDTO(Tournament tournament) {
        TournamentDto tournamentDTO = new TournamentDto();
        tournamentDTO.setTournamentId(tournament.getTournamentId());
        tournamentDTO.setName(tournament.getName());
        tournamentDTO.setCategory(tournament.getCategory());
        tournamentDTO.setStyle(tournament.getStyle());
        return tournamentDTO;
    }

    public Tournament toEntity(TournamentDto tournamentDTO) {
        Tournament tournament = new Tournament();
        tournament.setTournamentId(tournamentDTO.getTournamentId());
        tournament.setName(tournamentDTO.getName());
        tournament.setCategory(tournamentDTO.getCategory());
        tournament.setStyle(tournamentDTO.getStyle());
        return tournament;
    }
}
