package ru.cheezeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * Сущность фактического параметра
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "fact_param")
public class FactParametrEntity {
    /**
     * ID параметра
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Значение
     */
    @Column(name = "value")
    String value;

    /**
     * Внешний ключ на сущность {@link StrainEntity}
     */
    @ManyToOne
    @JoinColumn(name = "strain_id", nullable = false)
    StrainEntity strain;

    /**
     * Внешний ключ на сущность {@link PropertyEntity}
     */
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    PropertyEntity property;

    /**
     * Внешний ключ на сущность {@link SubPropertyEntity}
     */
    @ManyToOne
    @JoinColumn(name = "subproperty_id", nullable = false)
    SubPropertyEntity subProperty;

}
