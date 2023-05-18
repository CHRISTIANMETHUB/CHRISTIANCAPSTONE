package com.ses.playerservice.player;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @Test
    public void testGetAllPlayers() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Player> players = Arrays.asList(new Player(), new Player());
        Page<Player> playerPage = new PageImpl<>(players, pageable, players.size());

        when(playerService.getAllPlayers(pageable)).thenReturn(playerPage);

        ResponseEntity<Page<Player>> responseEntity = playerController.getAllPlayers(pageable);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(playerPage, responseEntity.getBody());
    }

    @Test
    public void testGetPlayerById() {
        Long playerId = 1L;
        Player player = new Player();
        player.setId(playerId);

        when(playerService.getPlayerById(playerId)).thenReturn(player);

        ResponseEntity<Player> responseEntity = playerController.getPlayerById(playerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(player, responseEntity.getBody());
    }
    @Test
    public void testCreatePlayer() {
        PlayerDto playerDto = new PlayerDto();
        Player player = new Player();

        when(playerService.toEntity(playerDto)).thenReturn(player);
        when(playerService.createPlayer(player)).thenReturn(player);

        ResponseEntity<Player> responseEntity = playerController.createPlayer(playerDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(player, responseEntity.getBody());
    }

    @Test
    public void testUpdatePlayer() {
        Long playerId = 1L;
        PlayerDto playerDto = new PlayerDto();
        Player player = new Player();

        when(playerService.toEntity(playerDto)).thenReturn(player);
        when(playerService.updatePlayer(playerId, player)).thenReturn(player);

        ResponseEntity<Player> responseEntity = playerController.updatePlayer(playerId, playerDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(player, responseEntity.getBody());
    }

    @Test
    public void testDeletePlayer() {
        Long playerId = 1L;

        playerController.deletePlayer(playerId);

        verify(playerService, times(1)).deletePlayer(playerId);
    }

    @Test
    public void testGetByTeamId() {
        Long teamId = 1L;
        List<Player> players = Arrays.asList(new Player(), new Player());

        when(playerService.findByTeamId(teamId)).thenReturn(players);

        ResponseEntity<List<Player>> responseEntity = playerController.getByTeamId(teamId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(players, responseEntity.getBody());
    }
}