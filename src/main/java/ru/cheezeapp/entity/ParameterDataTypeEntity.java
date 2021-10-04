package ru.cheezeapp.entity;

import javax.persistence.*;

@Entity
public class ParameterDataTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, nullable = false)
    String name;
}
