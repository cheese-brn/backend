package ru.cheezeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.cheezeapp.entity.DataTypeEntity;
import ru.cheezeapp.entity.PropertyEntity;

import java.util.List;

/**
 * Канальная модель подсвойства
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubProperty {

    /**
     * ID подсвойства
     */
    private Long id;

    /**
     * Наименование
     */
    private String name;

    /**
     * Свойство, к которому отностится подсвойство
     */
    @JsonIgnore
    private PropertyEntity property;

    /**
     * Тип данных подсвойства
     */
    private DataTypeEntity dataType;

    /**
     * Строки таблицы зависимостей, в которых данное подсвойство является первым параметром зависимости
     */
    @JsonIgnore
    private List<DependencyTable> firstSubproperty;

    /**
     * Строки таблицы зависимостей, в которых данное подсвойство является вторым параметром зависимости
     */
    @JsonIgnore
    private List<DependencyTable> secondSubproperty;
}
