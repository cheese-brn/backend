package ru.cheezeapp.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Сущность рода
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rod_strain")
public class RodStrainEntity {
    /**
     * ID рода
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
     * Связь один-ко-многим с {@link VidStrainEntity}
     */
    @OneToMany(targetEntity = VidStrainEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "rod_id", referencedColumnName = "id")
    List<VidStrainEntity> vids;
}
