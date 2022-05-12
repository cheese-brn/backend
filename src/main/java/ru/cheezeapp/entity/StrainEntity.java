package ru.cheezeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность штамма
 *
 * @author Nikolay Golovnev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "strain")
public class StrainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Другие сведения
     */
    @Column(name = "annotation", columnDefinition = "TEXT")
    String annotation;

    /**
     * Наименование
     */
    @Column(name = "exemplar", columnDefinition = "TEXT")
    String exemplar;

    /**
     * Модификация
     */
    @Column(name = "modification", columnDefinition = "TEXT")
    String modification;

    /**
     * Способ получения
     */
    @Column(name = "obtaining_method", columnDefinition = "TEXT")
    String obtainingMethod;

    /**
     * Происхождение
     */
    @Column(name = "origin", columnDefinition = "TEXT")
    String origin;

    /**
     * Внешний ключ на сущность {@link VidStrainEntity}
     */
    @ManyToOne
    @JoinColumn(name = "vid_id", nullable = false)
    VidStrainEntity vidStrain;

    /**
     * Удален ли штамм
     */
    @Column(name = "is_deleted")
    boolean deleted;

    /**
     * Утрачен ли штамм
     */
    @Column(name = "is_lost")
    boolean lost;

    /**
     * Связь один-ко-многим с {@link FactParametrEntity}
     */
    @OneToMany(targetEntity = FactParametrEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametrEntity> factParametrs;

    /**
     * Связь один-ко-многим с {@link FactParametrFuncEntity}
     */
    @OneToMany(targetEntity = FactParametrFuncEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametrFuncEntity> factParametrsFunc;

}
