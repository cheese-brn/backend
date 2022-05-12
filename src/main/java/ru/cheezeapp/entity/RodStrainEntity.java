package ru.cheezeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Сущность рода
 *
 * @author Nikolay Golovnev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "rod_strain")
public class RodStrainEntity {

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
     * Удален ли род
     */
    @Column(name = "is_deleted")
    boolean deleted;

    /**
     * Связь один-ко-многим с {@link VidStrainEntity}
     */
    @OneToMany(targetEntity = VidStrainEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "rod_id", referencedColumnName = "id")
    List<VidStrainEntity> vids;
}
