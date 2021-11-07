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
public class StrainEntity {
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

    @OneToMany(targetEntity = FactParametrEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametrEntity> factParametrs;

    @OneToMany(targetEntity = FactParametrFuncEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametrFuncEntity> factParametrsFunc;

    @Column
    String annotation;
}
