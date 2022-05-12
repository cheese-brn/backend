package ru.cheezeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность вида
 *
 * @author Nikolay Golovnev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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
    @Column(name = "name", columnDefinition = "TEXT")
    String name;

    /**
     * Удален ли вид
     */
    @Column(name = "is_deleted")
    boolean deleted;

    /**
     * Внешний ключ на сущность {@link RodStrainEntity}
     */
    @ManyToOne
    @JoinColumn(name = "rod_id", nullable = false)
    RodStrainEntity rodStrain;

    /**
     * Связь один-ко-многим с {@link StrainEntity}
     */
    @OneToMany(targetEntity = StrainEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "vid_id", referencedColumnName = "id")
    List<StrainEntity> strains;
}
