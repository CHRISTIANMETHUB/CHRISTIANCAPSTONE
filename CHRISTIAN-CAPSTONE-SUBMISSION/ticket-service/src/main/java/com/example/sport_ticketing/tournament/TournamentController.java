package com.example.sport_ticketing.tournament;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Tournament> createTournament(@RequestBody TournamentDto tournament) {
        Tournament savedTournament = tournamentService.save(tournamentService.toEntity(tournament));
        return ResponseEntity.ok(savedTournament);
    }

    @PutMapping("/update")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody TournamentDto tournament) {
        Tournament savedTournament = tournamentService.updateTournament(tournamentService.toEntity(tournament));
        return ResponseEntity.ok(savedTournament);
    }

    @GetMapping
    public Page<Tournament> getAllTournaments(Pageable page) {
        return tournamentService.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTournamentById(@PathVariable Long id) {
        tournamentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}