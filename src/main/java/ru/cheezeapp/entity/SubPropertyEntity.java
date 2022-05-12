package ru.cheezeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность подсвойства
 *
 * @author Nikolay Golovnev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "subproperty")
public class SubPropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Наименование
     */
    @Column(name = "name", columnDefinition = "TEXT")
    String name;

    /**
     * Единица измерения
     */
    @Column(name = "unit")
    String unit;

    /**
     * Шифр
     */
    @Column(name = "cypher", columnDefinition = "bigint")
    @EqualsAndHashCode.Exclude
    Integer cypher;

    /**
     * Внешний ключ на сущность {@link PropertyEntity}
     */
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    @EqualsAndHashCode.Exclude
    PropertyEntity property;

    /**
     * Внешний ключ на сущность {@link DataTypeEntity}
     */
    @ManyToOne
    @JoinColumn(name = "datatype_id", nullable = false)
    DataTypeEntity dataType;

    /**
     * Связь один-ко-многим с {@link FactParametrEntity}
     */
    @OneToMany(mappedBy = "subProperty", targetEntity = FactParametrEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    List<FactParametrEntity> factParametrs;

    /**
     * Связь один-ко-многим с {@link DependencyTableEntity} для первого параметра зависимости
     */
    @OneToMany(targetEntity = DependencyTableEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "first_subproperty_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    List<DependencyTableEntity> firstSubproperty;

    /**
     * Связь один-ко-многим с {@link DependencyTableEntity} для второго параметра зависимости
     */
    @OneToMany(targetEntity = DependencyTableEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "second_subproperty_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    List<DependencyTableEntity> secondSubproperty;
}
