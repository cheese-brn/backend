package ru.cheezeapp.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "strain")
public class Strain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String exemplar;

    @Column
    String modification;

    @Column
    String obtainingMethod;

    @Column
    String origin;

    @OneToMany(targetEntity = FactParametr.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametr> factParametrs;

    @OneToMany(targetEntity = FactParametrFunc.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametrFunc> factParametrsFunc;

    @Column
    String annotation;
}
