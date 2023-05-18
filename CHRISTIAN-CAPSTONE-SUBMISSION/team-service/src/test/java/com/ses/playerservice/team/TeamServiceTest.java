package com.ses.playerservice.team;

import com.ses.playerservice.teams.Team;
import com.ses.playerservice.teams.TeamRepository;
import com.ses.playerservice.teams.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    public void testGetAllTeams() {
        // Create a Pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // Create a list of teams
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1L, "Team 1"));
        teams.add(new Team(2L, "Team 2"));

        // Mock the behavior of the teamRepository.findAll() method to return the list of teams
        when(teamRepository.findAll(pageable)).thenReturn(new PageImpl<>(teams));

        // Call the getAllTeams() method of the teamService
        Page<Team> result = teamService.getAllTeams(pageable);

        // Verify that the teamRepository.findAll() method was called once
        verify(teamRepository, times(1)).findAll(pageable);

        // Verify that the result contains the same teams as the list of teams
        assertEquals(teams, result.getContent());
    }

    @Test
    public void testGetTeamById() {
        // Create a team
        Team team = new Team(1L, "Team 1");

        // Mock the behavior of the teamRepository.findById() method to return the team
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        // Call the getTeamById() method of the teamService
        Team result = teamService.getTeamById(1L);

        // Verify that the teamRepository.findById() method was called once
        verify(teamRepository, times(1)).findById(1L);

        // Verify that the result is the same as the team
        assertEquals(team, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetTeamByIdNotFound() {
        // Mock the behavior of the teamRepository.findById() method to return an empty optional
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the getTeamById() method of the teamService, expecting a NoSuchElementException to be thrown
        teamService.getTeamById(1L);

        // Verify that the teamRepository.findById() method was called once
        verify(teamRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateTeam() {
        // Create a team
        Team team = new Team(1L, "Team 1");

        // Mock the behavior of the teamRepository.save() method to return the team
        when(teamRepository.save(team)).thenReturn(team);

        // Call the createTeam() method of the teamService
        Team result = teamService.createTeam(team);

        // Verify that the teamRepository.save() method was called once
        verify(teamRepository, times(1)).save(team);

        // Verify that the result is the same as the team
        assertEquals(team, result);
    }

    @Test
    public void testUpdateTeam() {
        // Create a team
        Team team = new Team(1L, "Team 1");

        // Mock the behavior of the teamRepository.findById() method to return the team
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        // Mock the behavior of the teamRepository.save() method to return the updated team
        when(teamRepository.save(team)).thenReturn(team);

        // Call the updateTeam() method of the teamService
        Team result = teamService.updateTeam(1L, team);

        // Verify that the teamRepository.findById() method was called once
        verify(teamRepository, times(1)).findById(1L);

        // Verify that the teamRepository.save() method was called once
        verify(teamRepository, times(1)).save(team);
    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdateTeamNotFound() {
        // Create a team
        Team team = new Team(1L, "Team 1");

        // Mock the behavior of the teamRepository.findById() method to return an empty optional
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the updateTeam() method of the teamService, expecting a NoSuchElementException to be thrown
        teamService.updateTeam(1L, team);

        // Verify that the teamRepository.findById() method was called once
        verify(teamRepository, times(1)).findById(1L);

        // Verify that the teamRepository.save() method was not called
        verify(teamRepository, times(0)).save(team);
    }

    @Test
    public void testDeleteTeam() {
        // Call the deleteTeam() method of the teamService
        teamService.deleteTeam(1L);

        // Verify that the teamRepository.deleteById() method was called once with the same id as the parameter of the deleteTeam() method
        verify(teamRepository, times(1)).deleteById(1L);
    }
}