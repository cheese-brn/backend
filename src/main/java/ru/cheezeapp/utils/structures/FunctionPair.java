package ru.cheezeapp.utils.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.List;

/**
 * Класс функции. Представляет из себя пару, где первый элемент - список аргументов, а второй - наименование
 *
 * @author Pavel Chupikov
 */
@Setter
@Getter
@AllArgsConstructor
public class FunctionPair {

    /**
     * Список аргументов, всего их 2.
     */
    private List<SubPropertyEntity> fst;

    /**
     * Наименование функции
     */
    private String snd;

}
