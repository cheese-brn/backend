package ru.cheezeapp.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * Сущность вида
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vid_strain")
public class VidStrainEntity {
    /**
     * ID вида
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
     * Наименование
     */
    @Column(name = "name")
    String name;

    /**
     * Внешний ключ на сущность {@link RodStrainEntity}
     */
    @ManyToOne
    @JoinColumn(name = "rod_id", nullable = false)
    RodStrainEntity rodStrain;

    /**
     * Связь один-ко-многим с {@link StrainEntity}
     */
    @OneToMany(targetEntity = StrainEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "vid_id", referencedColumnName = "id")
    List<StrainEntity> strains;
}
