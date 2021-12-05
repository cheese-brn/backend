package ru.cheezeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.entity.SubPropertyEntity;

/**
 * Канальная модель фактического параметра
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactParametr {

    /**
     * ID параметра
     */
    private Long id;

    /**
     * Значение
     */
    private String value;

    /**
     * Штамм, к которому относится данный параметр
     */
    private StrainEntity strain;

    /**
     * Свойство, к которому относится данный параметр
     */
    private PropertyEntity property;

    /**
     * Подсвойство, к которому относится данный параметр
     */
    private SubPropertyEntity subProperty;

}
