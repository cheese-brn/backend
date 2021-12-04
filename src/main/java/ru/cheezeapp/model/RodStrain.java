package ru.cheezeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

/**
 * Канальная модель рода
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RodStrain {

    /**
     * ID рода
     */
    private Long id;

    /**
     * Шифр
     */
    private Long cypher;

    /**
     * Наименование
     */
    private String name;

    /**
     * Список видов рода
     */
    @JsonIgnore
    private List<VidStrain> vidStrains;
}
