package com.hella.ICTManager.models.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity   //imi face clasa sa fie o tabela intr-o baza de date
@Table(name = "machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String equipmentName;

    private int internalFactory;

    private String serialNumber;

    private String equipmentType;

    @ManyToMany(mappedBy = "machines")
    private Set<Fixture> fixtures; //o masina are o lista de fixture-uri
}
