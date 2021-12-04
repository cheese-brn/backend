package ru.cheezeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.cheezeapp.entity.FactParametrEntity;

import java.util.List;

/**
 * Канальная модель свойства
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    /**
     * ID свойства
     */
    private Long id;

    /**
     * Шифр
     */
    private Long cypher;

    /**
     * Описание
     */
    private String description;

    /**
     * Наименование
     */
    private String name;

    /**
     * Функция или не функция (1 или 0)
     */
    private Boolean propertyType;

    /**
     * Список фактических параметров
     */
    @JsonIgnore
    private List<FactParametrEntity> factParametrs;

    /**
     * Список подсвойств
     */
    private List<SubProperty> subProperties;
}
