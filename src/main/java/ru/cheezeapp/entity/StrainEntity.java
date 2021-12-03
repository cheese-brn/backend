package ru.cheezeapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * Сущность штамма
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "strain")
public class StrainEntity {
    /**
     * ID штамма
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Другие сведения
     */
    @Column(name = "annotation")
    String annotation;

    /**
     * Наименование
     */
    @Column(name = "exemplar")
    String exemplar;

    /**
     * Модификация
     */
    @Column(name = "modification")
    String modification;

    /**
     * Способ получения
     */
    @Column(name = "obtaining_method")
    String obtainingMethod;

    /**
     * Происхождение
     */
    @Column(name = "origin")
    String origin;

    /**
     * Внешний ключ на сущность {@link VidStrainEntity}
     */
    @ManyToOne
    @JoinColumn(name = "vid_id", nullable = false)
    VidStrainEntity vidStrain;

    /**
     * Связь один-ко-многим с {@link FactParametrEntity}
     */
    @OneToMany(targetEntity = FactParametrEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametrEntity> factParametrs;

    /**
     * Связь один-ко-многим с {@link FactParametrFuncEntity}
     */
    @OneToMany(targetEntity = FactParametrFuncEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "strain_id", referencedColumnName = "id")
    List<FactParametrFuncEntity> factParametrsFunc;

}
