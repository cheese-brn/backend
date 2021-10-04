package ru.cheezeapp.entity;

import javax.persistence.*;

@Entity
public class StrainGenusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;
}
