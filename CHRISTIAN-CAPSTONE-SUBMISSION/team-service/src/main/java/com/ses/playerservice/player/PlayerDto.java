package com.ses.playerservice.player;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private Long teamId;

    public PlayerDto(Player player) {
        this.id = player.getId();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.country = player.getCountry();
        this.teamId = player.getTeam().getId();
    }

}