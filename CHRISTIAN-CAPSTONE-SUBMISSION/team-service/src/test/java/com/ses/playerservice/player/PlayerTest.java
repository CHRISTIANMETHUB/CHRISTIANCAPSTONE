package com.ses.playerservice.player;

import com.ses.playerservice.teams.Team;
import com.ses.playerservice.teams.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void testPlayerGettersAndSetters() {
        // Create a Player object with test data
        Player player = new Player();
        player.setId(1L);
        player.setFirstName("John");
        player.setLastName("Doe");
        player.setCountry("USA");
        Team team = new Team();
        team.setId(1L);
        team.setTeamName("Test Team");
        player.setTeam(team);

        // Use the getters to verify that the values were set correctly
        assertEquals(1L, player.getId().longValue());
        assertEquals("John", player.getFirstName());
        assertEquals("Doe", player.getLastName());
        assertEquals("USA", player.getCountry());
        assertEquals(team, player.getTeam());

        // Change the values using the setters
        player.setId(2L);
        player.setFirstName("Jane");
        player.setLastName("Smith");
        player.setCountry("Canada");
        Team newTeam = new Team();
        newTeam.setId(2L);
        newTeam.setTeamName("New Team");
        player.setTeam(newTeam);

        // Verify that the new values were set correctly
        assertEquals(2L, player.getId().longValue());
        assertEquals("Jane", player.getFirstName());
        assertEquals("Smith", player.getLastName());
        assertEquals("Canada", player.getCountry());
        assertEquals(newTeam, player.getTeam());
    }

    @Test
    public void testConstructor() {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String country = "USA";
        Long teamId = 2L;

        Player player = new Player();
        player.setId(id);
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setCountry(country);
        Team team = new Team();
        team.setId(teamId);
        player.setTeam(team);

        PlayerDto playerDto = new PlayerDto(player);

        assertEquals(id, playerDto.getId());
        assertEquals(firstName, playerDto.getFirstName());
        assertEquals(lastName, playerDto.getLastName());
        assertEquals(country, playerDto.getCountry());
        assertEquals(teamId, playerDto.getTeamId());
    }

    @Test
    public void testSettersAndGetters() {
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String country = "USA";
        Long teamId = 2L;

        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(id);
        playerDto.setFirstName(firstName);
        playerDto.setLastName(lastName);
        playerDto.setCountry(country);
        playerDto.setTeamId(teamId);

        assertEquals(id, playerDto.getId());
        assertEquals(firstName, playerDto.getFirstName());
        assertEquals(lastName, playerDto.getLastName());
        assertEquals(country, playerDto.getCountry());
        assertEquals(teamId, playerDto.getTeamId());
    }

    @Test
    public void testToDto() {
        Player player = new Player();
        player.setId(1L);
        player.setFirstName("John");
        player.setLastName("Doe");
        player.setCountry("USA");

        Team team = new Team();
        team.setId(1L);

        player.setTeam(team);

        PlayerDto dto = playerService.toDto(player);

        assertNotNull(dto);
        assertEquals(dto.getId(), player.getId());
        assertEquals(dto.getFirstName(), player.getFirstName());
        assertEquals(dto.getLastName(), player.getLastName());
        assertEquals(dto.getCountry(), player.getCountry());
        assertEquals(dto.getTeamId(), player.getTeam().getId());
    }

    @Test
    public void testToEntity() {
        PlayerDto dto = new PlayerDto();
        dto.setId(1L);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setCountry("USA");
        dto.setTeamId(1L);

        Team team = new Team();
        team.setId(dto.getTeamId());

        when(teamRepository.findById(dto.getTeamId())).thenReturn(Optional.of(team));

        Player player = playerService.toEntity(dto);

        assertNotNull(player);
        assertEquals(player.getId(), dto.getId());
        assertEquals(player.getFirstName(), dto.getFirstName());
        assertEquals(player.getLastName(), dto.getLastName());
        assertEquals(player.getCountry(), dto.getCountry());
        assertEquals(player.getTeam().getId(), dto.getTeamId());
    }

    @Test
    public void testToDtoList() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setFirstName("Test");
        player1.setLastName("Player1");
        player1.setCountry("USA");

        Team team1 = new Team();
        team1.setId(1L);
        player1.setTeam(team1);

        Player player2 = new Player();
        player2.setId(2L);
        player2.setFirstName("Test");
        player2.setLastName("Player2");
        player2.setCountry("USA");

        Team team2 = new Team();
        team2.setId(2L);
        player2.setTeam(team2);

        List<Player> players = Arrays.asList(player1, player2);

        List<PlayerDto> dtos = playerService.toDtoList(players);

        assertNotNull(dtos);
        assertEquals(dtos.size(), players.size());

        for (int i = 0; i < dtos.size(); i++) {
            PlayerDto dto = dtos.get(i);
            Player player = players.get(i);

            assertEquals(dto.getId(), player.getId());
            assertEquals(dto.getFirstName(), player.getFirstName());
            assertEquals(dto.getLastName(), player.getLastName());
            assertEquals(dto.getCountry(), player.getCountry());
            assertEquals(dto.getTeamId(), player.getTeam().getId());
        }
    }
}

