package ru.cheezeapp.entity;

import javax.persistence.*;

@Entity
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String code;

    @Column(unique = true, nullable = false)
    String name;

    @Column(nullable = false)
    Boolean isNote;
}
