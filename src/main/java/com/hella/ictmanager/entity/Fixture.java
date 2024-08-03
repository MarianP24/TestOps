package com.hella.ictmanager.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "fixture")
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fileName;

    private String programName;

    private String productName;

    private String business;

    private int counter;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "fixture_machine",
            joinColumns = @JoinColumn(name = "fixture_id"),
            inverseJoinColumns = @JoinColumn(name = "machine_id")
    )
    @JsonIgnore
    private Set<Machine> machines = new HashSet<>(); //un fixture are o lista de masini
}
