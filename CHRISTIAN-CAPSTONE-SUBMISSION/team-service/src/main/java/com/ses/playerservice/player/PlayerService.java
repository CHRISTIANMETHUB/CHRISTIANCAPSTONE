package com.ses.playerservice.player;

import com.ses.playerservice.teams.Team;
import com.ses.playerservice.teams.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Page<Player> getAllPlayers(Pageable page) {
        return playerRepository.findAll(page);
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Player not found with id " + id));
    }

    public Player createPlayer(Player player) {
        if(teamRepository.findById(player.getTeam().getId()).isEmpty()){
            throw new NoSuchElementException("Team you are trying to insert to player does not exists");
        }
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player playerDetails) {


        Player player = getPlayerById(id);
        player.setFirstName(playerDetails.getFirstName());
        player.setLastName(playerDetails.getLastName());
        player.setCountry(playerDetails.getCountry());
        player.setTeam(playerDetails.getTeam());
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        Player player = getPlayerById(id);
        playerRepository.delete(player);
    }

    public List<Player> findByTeamId(Long teamId) {
        return playerRepository.findAllByTeamId(teamId);
    }

    public PlayerDto toDto(Player player) {
        return new PlayerDto(player);
    }

    public Player toEntity(PlayerDto dto) {
        Player player = new Player();
        player.setId(dto.getId());
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setCountry(dto.getCountry());
        Optional<Team> teamOptional = teamRepository.findById(dto.getTeamId());

        if(teamOptional.isEmpty()){
            throw new NoSuchElementException("Team you are trying to insert to player does not exists");
        } else {
            player.setTeam(teamOptional.get()) ;
        }
        return player;
    }

    public List<PlayerDto> toDtoList(List<Player> players) {
        return players.stream().map(this::toDto).collect(Collectors.toList());
    }
}