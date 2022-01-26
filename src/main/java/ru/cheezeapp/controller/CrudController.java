package ru.cheezeapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.service.property.PropertyCrudService;
import ru.cheezeapp.service.strain.StrainCrudService;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;

/**
 * Crud контроллер для обработки запросов, связанных
 */
@RestController
@Slf4j
public class CrudController {

    @Autowired
    StrainCrudService strainCrudService;

    @Autowired
    PropertyCrudService propertyCrudService;

    @Autowired
    JsonToObjectConverter jsonToObjectConverter;

    /**
     * Метод обработки запроса на мягкое удаление штамма
     *
     * @param id ID удаляемого штамма
     * @return сообщение об обработке
     */
    @PostMapping("/strain/delete/{id}")
    public String softDeletionOfStrainById(@PathVariable Long id) {
        log.info("[POST /strain/delete/" + id + "]\tEntered softDeletionOfStrainById() method");
        try {
            strainCrudService.softDeletionOfStrainById(id);
            log.info("[POST /strain/delete/" + id + "]\tSoft deleted strain with id: " + id);
            return "Штамм помещён в корзину";
        } catch (Exception e) {
            log.info("[POST /strain/delete/" + id + "]\tThrows exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Метод обработки запроса на полное удаление штамма из БД
     *
     * @param id ID удаляемого штамма
     * @return сообщение об обработке
     */
    @PostMapping("/strain/hard_delete/{id}")
    public String hardDeletionOfStrainById(@PathVariable Long id) {
        log.info("[POST /strain/hard_delete/" + id + "]\tEntered hardDeletionOfStrainById() method");
        try {
            strainCrudService.hardDeletionOfStrainById(id);
            log.info("[POST /strain/hard_delete/" + id + "]\tHard deleted strain with id: " + id);
            return "Штамм успешно удалён";
        } catch (Exception e) {
            log.info("[POST /strain/hard_delete/" + id + "]\tThrows exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Метод обработки запроса на добавление или обновления штамма.
     * Если ID штамма == 0, значит добавяем, иначе, редактируем.
     *
     * @param strainJson JSON штамма
     * @return сообщение об обработке
     */
    @PostMapping("/strain/send")
    public String sendStrain(@RequestBody String strainJson) {
        log.info("[POST /strain/send/]\tEntered sendStrain() method");
        try {
            StrainEntity strain = jsonToObjectConverter.jsonToStrain(strainJson);
            if (strain.getId() == 0) {
                strainCrudService.addStrain(strain);
                log.info("[POST /strain/send/]\tNew strain was created");
                return "Штамм был успешно добавлен";
            } else {
                strainCrudService.updateStrain(strain);
                log.info("[POST /strain/send/]\tStrain was updated, id = " + strain.getId());
                return "Штамм был успешно обновлен";
            }
        } catch (Exception e) {
            log.info("[POST /strain/send/]\tThrows exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Метод удаления всех штаммов из корзины
     *
     * @return сообщение об обработке
     */
    @PostMapping("/strain/hard_delete_all")
    public String hardDeleteAllStrains() {
        log.info("[POST /strain/hard_delete_all/]\tEntered hardDeleteAllStrains() method");
        try {
            strainCrudService.hardDeleteAllStrains();
            log.info("[POST /strain/hard_delete_all/]\tAll deleted strains were hardly deleted");
            return "Корзина очищена";
        } catch (Exception e) {
            log.info("[POST /strain/hard_delete_all/]\tThrows exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Метод восстановления штамма из корзины
     *
     * @param id ID восстанавливаемого штамма
     * @return сообщение об обработке
     */
    @PostMapping("/strain/restore/{id}")
    public String restoreStrain(@PathVariable Long id) {
        log.info("[POST /strain/restore/" + id + "]\tEntered restoreStrain() method");
        try {
            strainCrudService.restoreStrain(id);
            log.info("[POST /strain/restore/" + id + "]\tStrain was restored from bucket");
            return "Штамм восстновлен из корзины";
        } catch (Exception e) {
            log.info("[POST /strain/restore/" + id + "]\tThrows exception: " + e.getMessage());
            return e.getMessage();
        }
    }

}
