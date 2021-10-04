package ru.cheezeapp.entity;

import javax.persistence.*;

//TODO: в исходниках это нигде не используется, нужно будет думать куда это сувать
@Entity
public class SetOfValuesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String value;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    FormalParameterEntity formalParameter;
}
