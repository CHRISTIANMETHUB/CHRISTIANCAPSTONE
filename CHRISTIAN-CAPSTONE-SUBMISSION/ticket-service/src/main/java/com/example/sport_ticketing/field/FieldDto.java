package com.example.sport_ticketing.field;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldDto {

    private Long id;
    private String name;

    private String address;

    private Integer capacity;

    @Override
    public String toString() {
        return "FieldDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
