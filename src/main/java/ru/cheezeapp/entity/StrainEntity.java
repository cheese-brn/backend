package ru.cheezeapp.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class StrainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    LocalDate dateReceiving;

    @Column(nullable = false)
    String collectionIndex;

    @Column(nullable = false)
    String source;

    @Column(nullable = false)
    LocalDate dateAdded;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    StrainTypeEntity type;

    @Column(nullable = false)
    String obtainingMethod;
}
