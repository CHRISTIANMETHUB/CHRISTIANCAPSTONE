package com.example.sport_ticketing.field;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "field")
@AllArgsConstructor
@NoArgsConstructor
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Long fieldId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;
}