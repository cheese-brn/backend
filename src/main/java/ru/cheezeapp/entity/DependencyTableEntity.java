package ru.cheezeapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность таблицы зависимостей
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dependency_table")
public class DependencyTableEntity {
    /**
     * ID зависимости
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Внешний ключ на сущность {@link SubPropertyEntity} для первого подсвойства зависимости
     */
    @ManyToOne
    @JoinColumn(name = "first_subproperty_id", nullable = false)
    SubPropertyEntity firstSubProperty;

    /**
     * Внешний ключ на сущность {@link SubPropertyEntity} для второго подсвойства зависимости
     */
    @ManyToOne
    @JoinColumn(name = "second_subproperty_id", nullable = false)
    SubPropertyEntity secondSubProperty;

    @OneToMany(targetEntity = FactParametrFuncEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dependency_table_id", referencedColumnName = "id")
    List<FactParametrFuncEntity> factParametrsFunc;
}
