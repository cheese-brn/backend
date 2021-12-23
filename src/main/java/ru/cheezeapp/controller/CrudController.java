package ru.cheezeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.service.strain.StrainCrudService;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;

/**
 * Crud контроллер для обработки запросов, связанных
 */
@RestController
public class CrudController {

    @Autowired
    StrainCrudService strainCrudService;

    @Autowired
    JsonToObjectConverter jsonToObjectConverter;

    /**
     * Метод обработки запроса на удаление штамма из БД
     *
     * @param id ID удаляемого штамма
     * @return сообщение об обработке
     */
    @PostMapping("/strain/delete/{id}")
    public String deleteStrainById(@PathVariable Long id) {
        try {
            strainCrudService.deleteStrainById(id);
            return "good :)";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Метод обработки запроса на добавление или штамма из БД.
     * Если ID штамма = null, значит добавяем, иначе, редактируем.
     *
     * @param strainJson JSON штамма
     * @return сообщение об обработке
     */
    @PostMapping("/strain/send")
    public String sendStrain(@RequestBody String strainJson) {
        try {
            StrainEntity strain = jsonToObjectConverter.JsonToStrain(strainJson);
            if (strain.getId() == null)
                strainCrudService.addStrain(strain);
            else
                strainCrudService.updateStrain(strain);
            return "good :)";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

}
