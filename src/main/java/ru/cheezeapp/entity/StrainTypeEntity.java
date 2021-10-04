package ru.cheezeapp.entity;

import javax.persistence.*;

@Entity
public class StrainTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String name;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    StrainGenusEntity genus;
}
