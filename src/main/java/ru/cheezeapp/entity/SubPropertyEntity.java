package ru.cheezeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность подсвойства
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "subproperty")
public class SubPropertyEntity {
    /**
     * ID подсвойства
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Наименование
     */
    @Column(name = "name")
    String name;

    /**
     * Внешний ключ на сущность {@link PropertyEntity}
     */
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
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
    @OneToMany(targetEntity = FactParametrEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    List<FactParametrEntity> factParametrs;

    /**
     * Связь один-ко-многим с {@link DependencyTableEntity} для первого параметра зависимости
     */
    @OneToMany(targetEntity = DependencyTableEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "first_subproperty_id", referencedColumnName = "id")
    List<DependencyTableEntity> firstSubproperty;

    /**
     * Связь один-ко-многим с {@link DependencyTableEntity} для второго параметра зависимости
     */
    @OneToMany(targetEntity = DependencyTableEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "second_subproperty_id", referencedColumnName = "id")
    List<DependencyTableEntity> secondSubproperty;
}
