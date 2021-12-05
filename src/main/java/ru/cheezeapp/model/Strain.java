package ru.cheezeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.cheezeapp.entity.FactParametrEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;

/**
 * Канальная модель штамма
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Strain {

    /**
     * ID штамма
     */
    private Long id;

    /**
     * Род
     */
    private String rod;

    /**
     * Вид
     */
    private String vid;

    /**
     * Другие сведения
     */
    private String annotation;

    /**
     * Наименование
     */
    private String exemplar;

    /**
     * Модификация
     */
    private String modification;

    /**
     * Способ получения
     */
    private String obtainingMethod;

    /**
     * Происхождение
     */
    private String origin;

    /**
     * Внешний ключ на штамм
     */
    @JsonIgnore
    private VidStrainEntity vidStrain;

    /**
     * Фактические параметры
     */
    private List<FactParametr> factParametrs;

    /**
     * Фактические параметры функции
     */
    private List<FactParametrFunc> factParametrsFunc;
}
