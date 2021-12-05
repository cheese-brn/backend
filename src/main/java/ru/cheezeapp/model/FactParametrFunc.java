package ru.cheezeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.cheezeapp.entity.DependencyTableEntity;
import ru.cheezeapp.entity.StrainEntity;

/**
 * Канальная модель фактического параметра функции
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactParametrFunc {
    /**
     * ID
     */
    Long id;

    /**
     * Первый параметр
     */
    String firstParametr;

    /**
     * Второй параметр
     */
    String secondParametr;

    /**
     * Третий параметр
     */
    String thirdParametr;

    /**
     * Штамм, к которому относится фактический параметр функции
     */
    @JsonIgnore
    StrainEntity strain;

    /**
     * Зависимость, к которой относится фактический параметр функции
     */
    DependencyTableEntity dependencyTable;

}
