package ru.cheezeapp.controller.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.service.property.PropertyCrudService;
import ru.cheezeapp.service.strain.StrainCrudService;
import ru.cheezeapp.service.strain.StrainSearchService;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;
import ru.cheezeapp.utils.jsonConverter.ResponseToJsonConverter;

/**
 * Контроллер для обработки CRUD запросов, связанных со штаммами.
 */
@RestController
@Slf4j
public class StrainCrudController {

    @Autowired
    StrainCrudService strainCrudService;

    @Autowired
    PropertyCrudService propertyCrudService;

    @Autowired
    JsonToObjectConverter jsonToObjectConverter;

    @Autowired
    StrainSearchService strainSearchService;

    /**
     * Метод обработки запроса на мягкое удаление штамма
     *
     * @param id ID удаляемого штамма
     * @return сообщение об обработке
     */
    @GetMapping("/strain/delete/{id}")
    public String softDeletionOfStrainById(@PathVariable Long id) {
        log.info("[GET /strain/delete/" + id + "]\tEntered softDeletionOfStrainById() method");
        try {
            strainCrudService.softDeletionOfStrainById(id);
            log.info("[GET /strain/delete/" + id + "]\tSoft deleted strain with id: " + id);
            return ResponseToJsonConverter.responseToJson("Штамм помещён в корзину");
        } catch (Exception e) {
            log.info("[GET /strain/delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на полное удаление штамма из БД
     *
     * @param id ID удаляемого штамма
     * @return сообщение об обработке
     */
    @GetMapping("/strain/hard_delete/{id}")
    public String hardDeletionOfStrainById(@PathVariable Long id) {
        log.info("[GET /strain/hard_delete/" + id + "]\tEntered hardDeletionOfStrainById() method");
        try {
            strainCrudService.hardDeletionOfStrainById(id);
            log.info("[GET /strain/hard_delete/" + id + "]\tHard deleted strain with id: " + id);
            return ResponseToJsonConverter.responseToJson("Штамм успешно удалён");
        } catch (Exception e) {
            log.info("[GET /strain/hard_delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
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
                return ResponseToJsonConverter.strainCreationResponseToJson(strainSearchService
                        .findStrainIdByVidIdAndExemplarAndModification(strain.getVidStrain().getId(),
                                strain.getExemplar(), strain.getModification()));
            } else {
                strainCrudService.updateStrain(strain);
                log.info("[POST /strain/send/]\tStrain was updated, id = " + strain.getId());
                return ResponseToJsonConverter.responseToJson("Штамм был успешно обновлен");
            }
        } catch (Exception e) {
            log.info("[POST /strain/send/]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод удаления всех штаммов из корзины
     *
     * @return сообщение об обработке
     */
    @GetMapping("/strain/hard_delete_all")
    public String hardDeleteAllStrains() {
        log.info("[GET /strain/hard_delete_all/]\tEntered hardDeleteAllStrains() method");
        try {
            strainCrudService.hardDeleteAllStrains();
            log.info("[GET /strain/hard_delete_all/]\tAll deleted strains were hardly deleted");
            return ResponseToJsonConverter.responseToJson("Корзина очищена от штаммов");
        } catch (Exception e) {
            log.info("[GET /strain/hard_delete_all/]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод восстановления штамма из корзины
     *
     * @param id ID восстанавливаемого штамма
     * @return сообщение об обработке
     */
    @GetMapping("/strain/restore/{id}")
    public String restoreStrain(@PathVariable Long id) {
        log.info("[GET /strain/restore/" + id + "]\tEntered restoreStrain() method");
        try {
            strainCrudService.restoreStrain(id);
            log.info("[GET /strain/restore/" + id + "]\tStrain was restored from bucket");
            return ResponseToJsonConverter.responseToJson("Штамм восстновлен из корзины");
        } catch (Exception e) {
            log.info("[GET /strain/restore/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

}
