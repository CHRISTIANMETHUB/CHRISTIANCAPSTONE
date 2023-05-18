package com.ses.playerservice.team;

import com.ses.playerservice.teams.Team;
import com.ses.playerservice.teams.TeamDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TeamEntityTest {

    @Test
    public void testGettersAndSetters() {
        // Create a team
        Team team = new Team();
        team.setId(1L);
        team.setTeamName("Team 1");

        // Verify that the getters return the expected values
        Assert.assertEquals(1L, (long) team.getId());
        Assert.assertEquals("Team 1", team.getTeamName());

        // Create another team
        Team team2 = new Team(2L, "Team 2");

        // Verify that the getters return the expected values
        Assert.assertEquals(2L, (long) team2.getId());
        Assert.assertEquals("Team 2", team2.getTeamName());
    }

    @Test
    public void testGettersAndSettersDto() {
        // Create a team DTO
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamName("Team 1");

        // Verify that the getter returns the expected value
        Assert.assertEquals("Team 1", teamDto.getTeamName());

        // Create another team DTO
        TeamDto teamDto2 = new TeamDto("Team 2");

        // Verify that the getter returns the expected value
        Assert.assertEquals("Team 2", teamDto2.getTeamName());
    }
}