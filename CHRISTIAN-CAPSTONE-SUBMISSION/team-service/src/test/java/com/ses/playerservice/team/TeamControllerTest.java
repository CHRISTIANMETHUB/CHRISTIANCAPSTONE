package com.ses.playerservice.team;

import com.ses.playerservice.teams.Team;
import com.ses.playerservice.teams.TeamController;
import com.ses.playerservice.teams.TeamDto;
import com.ses.playerservice.teams.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamControllerTest {

    @Mock
    private TeamService teamServiceMock;

    @InjectMocks
    private TeamController teamController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTeamsTest() {
        // Create test data
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1L, "Team A"));
        teams.add(new Team(2L, "Team B"));
        Page<Team> page = new PageImpl<>(teams);
        Pageable pageable = mock(Pageable.class);
        // Set up mock service behavior
        when(teamServiceMock.getAllTeams(any(Pageable.class))).thenReturn(page);

        // Call controller method
        ResponseEntity<Page<Team>> response = teamController.getAllTeams(pageable);

        // Verify mock service was called
        verify(teamServiceMock, times(1)).getAllTeams(any(Pageable.class));

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teams, response.getBody().getContent());
    }

    @Test
    public void getTeamByIdTest() {
        // Create test data
        Long teamId = 1L;
        Team team = new Team(teamId, "Team A");
        when(teamServiceMock.getTeamById(teamId)).thenReturn(team);

        // Call controller method
        ResponseEntity<Team> response = teamController.getTeamById(teamId);

        // Verify mock service was called
        verify(teamServiceMock, times(1)).getTeamById(teamId);

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(team, response.getBody());
    }

    @Test
    public void createTeamTest() {
        // Create test data
        TeamDto teamDto = new TeamDto("Team A");

        // Set up mock service behavior
        when(teamServiceMock.createTeam(any(Team.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call controller method
        ResponseEntity<Team> response = teamController.createTeam(teamDto);

        // Verify mock service was called
        verify(teamServiceMock, times(1)).createTeam(any(Team.class));

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teamDto.getTeamName(), response.getBody().getTeamName());
    }

    @Test
    public void updateTeamTest() {
        // Create test data
        Long teamId = 1L;
        TeamDto teamDto = new TeamDto("Team A");
        Team team = new Team(teamId, "Team B");

        // Set up mock service behavior
        when(teamServiceMock.updateTeam(eq(teamId), any(Team.class))).thenReturn(team);

        // Call controller method
        ResponseEntity<Team> response = teamController.updateTeam(teamId, teamDto);

        // Verify mock service was called
        verify(teamServiceMock, times(1)).updateTeam(eq(teamId), any(Team.class));

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(team, response.getBody());
    }

    @Test
    public void deleteTeamTest() {
        // Create test data
        Long teamId = 1L;

        // Call controller method
        teamController.deleteTeam(teamId);

        // Verify mock service was called
        verify(teamServiceMock, times(1)).deleteTeam(teamId);

    }
}




