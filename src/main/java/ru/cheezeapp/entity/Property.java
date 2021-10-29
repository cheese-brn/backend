package ru.cheezeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Long cypher;

    /**
     * Функция или не функция (1 или 0)
     */
    @Column
    Boolean propertyType;

    @Column
    String description;

    @OneToMany(targetEntity = FactParametr.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    List<FactParametr> factParametrs;

    @OneToMany(targetEntity = SubProperty.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    List<SubProperty> subProperties;
}

