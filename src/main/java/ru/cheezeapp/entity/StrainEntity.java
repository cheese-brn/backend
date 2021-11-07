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

    /**
     * Наименование
     */
    @Column
    String exemplar;

    /**
     * Модификация
     */
    @Column
    String modification;

    /**
     * Способ получения
     */
    @Column
    String obtainingMethod;

    /**
     * Происхождение
     */
    @Column
    String origin;

    @OneToMany(targetEntity = FactParametrEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametrEntity> factParametrs;

    @OneToMany(targetEntity = FactParametrFuncEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametrFuncEntity> factParametrsFunc;

    /**
     * Другие сведения
     */
    @Column
    String annotation;
}
