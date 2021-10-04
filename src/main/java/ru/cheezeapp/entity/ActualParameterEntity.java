package ru.cheezeapp.entity;

import javax.persistence.*;

@Entity
public class ActualParameterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    StrainEntity strain;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    FormalParameterEntity formalParameter;
}
