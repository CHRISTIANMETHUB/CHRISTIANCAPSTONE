package com.ses.playerservice.player;

import com.ses.playerservice.teams.Team;
import com.ses.playerservice.teams.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void testGetAllPlayers() {
        Pageable page = PageRequest.of(0, 10);
        Page<Player> players = new PageImpl<>(Collections.emptyList());
        when(playerRepository.findAll(page)).thenReturn(players);

        Page<Player> result = playerService.getAllPlayers(page);

        verify(playerRepository).findAll(page);
        assertEquals(players, result);
    }

    @Test
    public void testGetPlayerById() {
        Long id = 1L;
        Player player = new Player();
        player.setId(id);
        when(playerRepository.findById(id)).thenReturn(Optional.of(player));

        Player result = playerService.getPlayerById(id);

        verify(playerRepository).findById(id);
        assertEquals(player, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetPlayerByIdNotFound() {
        Long id = 1L;
        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        playerService.getPlayerById(id);

        verify(playerRepository).findById(id);
    }

    @Test
    public void testCreatePlayer() {
        Long teamId = 1L;
        PlayerDto playerDto = new PlayerDto();
        playerDto.setTeamId(teamId);
        Player player = new Player();
        player.setId(1L);
        player.setTeam(new Team(teamId, "Test Team"));
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(new Team(teamId, "Test Team")));
        when(playerRepository.save(player)).thenReturn(player);

        Player result = playerService.createPlayer(player);

        verify(teamRepository).findById(teamId);
        verify(playerRepository).save(player);
        assertEquals(player, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void testCreatePlayerTeamNotFound() {
        Long teamId = 1L;
        PlayerDto playerDto = new PlayerDto();
        playerDto.setTeamId(teamId);
        Player player = new Player();
        player.setId(1L);
        player.setTeam(new Team(teamId, "Test Team"));
        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());

        playerService.createPlayer(player);

        verify(teamRepository).findById(teamId);
        verify(playerRepository, never()).save(player);
    }

    @Test
    public void testUpdatePlayer() {
        Long id = 1L;
        Player player = new Player();
        player.setId(id);
        player.setFirstName("Test");
        when(playerRepository.findById(id)).thenReturn(Optional.of(player));
        when(playerRepository.save(player)).thenReturn(player);

        Player result = playerService.updatePlayer(id, player);

        verify(playerRepository).findById(id);
        verify(playerRepository).save(player);
        assertEquals(player, result);
        assertEquals(player.getFirstName(), result.getFirstName());
    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdatePlayerNotFound() {
        Long id = 1L;
        Player player = new Player();
        player.setId(id);
        player.setFirstName("Test");
        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        playerService.updatePlayer(id, player);

        verify(playerRepository).findById(id);
        verify(playerRepository, never()).save(any());
    }

    @Test
    public void testDeletePlayer() {
        Long id = 1L;
        Player player = new Player();
        player.setId(id);
        player.setFirstName("Test");
        player.setLastName("Player");
        player.setCountry("Test Country");

        when(playerRepository.findById(id)).thenReturn(Optional.of(player));

        playerService.deletePlayer(id);

        verify(playerRepository).findById(id);
        verify(playerRepository).delete(player);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeletePlayerNotFound() {
        Long id = 1L;
        when(playerRepository.findById(id)).thenReturn(Optional.empty());

        playerService.deletePlayer(id);

        verify(playerRepository).findById(id);
        verify(playerRepository, never()).delete(any());
    }

    @Test
    public void testFindByTeamId() {
        Long teamId = 1L;
        Player player1 = new Player();
        player1.setId(1L);
        player1.setFirstName("Test");
        player1.setLastName("Player1");
        player1.setCountry("Test Country");
        player1.setTeam(new Team(teamId, "Test Team"));

        Player player2 = new Player();
        player2.setId(2L);
        player2.setFirstName("Test");
        player2.setLastName("Player2");
        player2.setCountry("Test Country");
        player2.setTeam(new Team(teamId, "Test Team"));

        List<Player> players = Arrays.asList(player1, player2);
        when(playerRepository.findAllByTeamId(teamId)).thenReturn(players);

        List<Player> result = playerService.findByTeamId(teamId);

        verify(playerRepository).findAllByTeamId(teamId);

        assertEquals(2, result.size());
        assertEquals("Player1", result.get(0).getLastName());
        assertEquals("Player2", result.get(1).getLastName());
    }

    @Test
    public void testToDto() {
        Player player = new Player();
        Team team = mock(Team.class);
        player.setId(1L);
        player.setFirstName("Test");
        player.setLastName("Player");
        player.setCountry("Test Country");
        player.setTeam(team);

        PlayerDto playerDto = playerService.toDto(player);

        assertEquals(1L, playerDto.getId().longValue());
        assertEquals("Test", playerDto.getFirstName());
        assertEquals("Player", playerDto.getLastName());
        assertEquals("Test Country", playerDto.getCountry());
    }

    @Test
    public void testToEntity() {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(1L);
        playerDto.setFirstName("Test");
        playerDto.setLastName("Player");
        playerDto.setCountry("Test Country");
        playerDto.setTeamId(1L);

        Team team = new Team(1L, "Test Team");
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        Player player = playerService.toEntity(playerDto);

        assertEquals(1L, player.getId().longValue());
        assertEquals("Test", player.getFirstName());
        assertEquals("Player", player.getLastName());
        assertEquals("Test Country", player.getCountry());
        assertEquals(team, player.getTeam());
    }

    @Test(expected = NoSuchElementException.class)
    public void testToEntityInvalidTeam() {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(1L);
        playerDto.setFirstName("Test");
        playerDto.setLastName("Player");
        playerDto.setCountry("Test Country");
        playerDto.setTeamId(1L);

        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        playerService.toEntity(playerDto);

        verify(teamRepository).findById(1L);
    }

    @Test
    public void testToDtoList() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setFirstName("Test");
        player1.setLastName("Player1");
        player1.setCountry("Country1");
        player1.setTeam(new Team(1L, "Team1"));

        Player player2 = new Player();
        player2.setId(2L);
        player2.setFirstName("Test");
        player2.setLastName("Player2");
        player2.setCountry("Country2");
        player2.setTeam(new Team(2L, "Team2"));

        List<Player> players = Arrays.asList(player1, player2);

        List<PlayerDto> dtos = playerService.toDtoList(players);

        assertEquals(2, dtos.size());
        assertEquals("Test", dtos.get(0).getFirstName());
        assertEquals("Player1", dtos.get(0).getLastName());
        assertEquals("Country1", dtos.get(0).getCountry());
        assertEquals(1L, dtos.get(0).getTeamId().longValue());

        assertEquals("Test", dtos.get(1).getFirstName());
        assertEquals("Player2", dtos.get(1).getLastName());
        assertEquals("Country2", dtos.get(1).getCountry());
        assertEquals(2L, dtos.get(1).getTeamId().longValue());
    }
}