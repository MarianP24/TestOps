package com.hella.ictmanager.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor // constructor fara parametri folosit de hibernate sa gestioneze instantele entitatilor
@Setter
@Getter
@Entity // clasa este o entitate mapata la o tabela din baza de date
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

    private long fixtureCounterSet;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "fixture_machine",
            joinColumns = @JoinColumn(name = "fixture_id"),
            inverseJoinColumns = @JoinColumn(name = "machine_id")
    )
    private Set<Machine> machines = new HashSet<>(); //un fixture are o lista de masini, un set gol init
}
