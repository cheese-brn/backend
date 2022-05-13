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
 *
 * @author Pavel Chupikov
 */
@RestController
@Slf4j
@CrossOrigin
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

}
