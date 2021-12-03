package ru.cheezeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность свойства
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "property")
public class PropertyEntity {
    /**
     * ID свойства
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Шифр
     */
    @Column(name = "cypher")
    Long cypher;

    /**
     * Описание
     */
    @Column(name = "description")
    String description;

    /**
     * Имя
     */
    @Column(name = "name")
    String name;

    /**
     * Функция или не функция (1 или 0)
     */
    @Column(name = "property_type")
    Boolean propertyType;

    /**
     * Связь один-ко-многим с {@link FactParametrEntity}
     */
    @OneToMany(targetEntity = FactParametrEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    List<FactParametrEntity> factParametrs;

    /**
     * Связь один-ко-многим с {@link SubPropertyEntity}
     */
    @OneToMany(targetEntity = SubPropertyEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    List<SubPropertyEntity> subProperties;
}

