package com.example.sport_ticketing.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @GetMapping
    public ResponseEntity<Page<Match>> getAllMatches(Pageable page) {
        Page<Match> match = matchService.getAllMatches(page);
        return ResponseEntity.ok(match);
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody MatchDto match) {
        Match newMatch = matchService.toMatch(match);
        Match createdMatch = matchService.saveMatch(newMatch);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody MatchDto match) {
        Match newMatch = matchService.toMatch(match);
        newMatch.setMatchId(id);
        Match updatedMatch = matchService.saveMatch(newMatch);
        return ResponseEntity.ok().body(updatedMatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id){
        matchService.deleteMatchById(id);
        return ResponseEntity.noContent().build();
    }


}