package ru.cheezeapp.entity;

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
    @OneToMany(targetEntity = SubPropertyEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "datatype_id", referencedColumnName = "id")
    List<SubPropertyEntity> subProperties;
}
