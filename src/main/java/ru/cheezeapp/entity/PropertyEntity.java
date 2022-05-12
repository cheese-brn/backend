package ru.cheezeapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность свойства
 *
 * @author Nikolay Golovnev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode
@Table(name = "property")
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Шифр
     */
    @Column(name = "cypher")
    Integer cypher;

    /**
     * Описание
     */
    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    /**
     * Имя
     */
    @Column(name = "name", columnDefinition = "TEXT")
    String name;

    /**
     * Функция или не функция (1 или 0)
     */
    @Column(name = "property_type")
    Boolean propertyType;

    /**
     * Удалено ли свойство
     */
    @Column(name = "is_deleted")
    boolean deleted;

    /**
     * Связь один-ко-многим с {@link FactParametrEntity}
     */
    @OneToMany(targetEntity = FactParametrEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    List<FactParametrEntity> factParametrs;

    /**
     * Связь один-ко-многим с {@link SubPropertyEntity}
     */
    @OneToMany(targetEntity = SubPropertyEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    List<SubPropertyEntity> subProperties;

    /**
     * Связь один-ко-многим с {@link DependencyTableEntity}
     */
    @OneToMany(targetEntity = DependencyTableEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    List<DependencyTableEntity> dependencies;

}

