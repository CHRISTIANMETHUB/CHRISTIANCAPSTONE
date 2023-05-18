package com.ses.playerservice.teams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<Page<Team>> getAllTeams(Pageable page) {
        return  ResponseEntity.ok(teamService.getAllTeams(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody TeamDto teamDto) {
        Team team = new Team(null, teamDto.getTeamName());
        return ResponseEntity.ok(teamService.createTeam(team));
    }

    @PutMapping("/update")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody TeamDto teamDto) {
        Team team = new Team(id, teamDto.getTeamName());
        return  ResponseEntity.ok(teamService.updateTeam(id, team));
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
