package ru.cheezeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность типа данных
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "data_type")
public class DataTypeEntity {
    /**
     * ID типа данных
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
     * Связь один-ко-многим с {@link SubPropertyEntity} для первого параметра зависимости
     */
    @OneToMany(targetEntity = SubPropertyEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "datatype_id", referencedColumnName = "id")
    @JsonIgnore
    List<SubPropertyEntity> subProperties;
}
