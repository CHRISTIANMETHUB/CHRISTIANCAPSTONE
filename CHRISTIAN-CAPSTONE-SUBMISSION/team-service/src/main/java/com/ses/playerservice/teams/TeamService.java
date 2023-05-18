package com.ses.playerservice.teams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Page<Team> getAllTeams(Pageable page) {
        return teamRepository.findAll(page);
    }

    public Team getTeamById(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if(team.isPresent()) {
            return team.get();
        } else {
            throw new NoSuchElementException("Team not found with id: " + id);
        }
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team team) {
        Optional<Team> existingTeam = teamRepository.findById(id);
        if(existingTeam.isPresent()) {
            team.setId(id);
            return teamRepository.save(team);
        } else {
            throw new NoSuchElementException("Team not found with id: " + id);
        }
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}