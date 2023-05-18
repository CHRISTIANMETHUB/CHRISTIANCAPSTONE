package com.ses.playerservice.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<Page<Player>> getAllPlayers(Pageable page) {
        return ResponseEntity.ok(playerService.getAllPlayers(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody PlayerDto player) {
        return  ResponseEntity.ok(playerService.createPlayer(playerService.toEntity(player)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody PlayerDto player) {
        return  ResponseEntity.ok(playerService.updatePlayer(id, playerService.toEntity(player)));
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }

    @GetMapping("getByTeamId/{teamId}")
    public ResponseEntity<List<Player>> getByTeamId(@PathVariable Long teamId) {
        return ResponseEntity.ok(playerService.findByTeamId(teamId));
    }
}
