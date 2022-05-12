package ru.cheezeapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность таблицы зависимостей
 *
 * @author Nikolay Golovnev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "dependency_table")
public class DependencyTableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Наименование функции
     */
    @Column(name = "function_name", columnDefinition = "TEXT")
    String functionName;

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

    /**
     * Внешний ключ на фактические параметры функции
     */
    @OneToMany(targetEntity = FactParametrFuncEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dependency_table_id", referencedColumnName = "id")
    List<FactParametrFuncEntity> factParametrsFunc;

    /**
     * Внешний ключ на сущность {@link PropertyEntity}
     */
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    PropertyEntity property;

}
