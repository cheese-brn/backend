package ru.cheezeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.cheezeapp.entity.FactParametrFuncEntity;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.List;

/**
 * Канальная модель таблицы зависимостей
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DependencyTable {

    /**
     * ID зависимости
     */
    Long id;

    /**
     * Первое подсвойство зависимости
     */
    SubPropertyEntity firstSubProperty;

    /**
     * Второе подсвойство зависимости
     */
    SubPropertyEntity secondSubProperty;

    @JsonIgnore
    List<FactParametrFuncEntity> factParametrsFunc;

}
