package ru.cheezeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

/**
 * Канальная модель вида
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VidStrain {

    /**
     * ID вида
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
     * Список штаммов вида
     */
    @JsonIgnore
    private List<Strain> strains;
}
