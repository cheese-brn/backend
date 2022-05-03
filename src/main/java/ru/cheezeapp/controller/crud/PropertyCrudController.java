package ru.cheezeapp.controller.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.service.property.PropertyCrudService;
import ru.cheezeapp.service.property.PropertySearchService;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;
import ru.cheezeapp.utils.jsonConverter.ResponseToJsonConverter;

/**
 * Контроллер для обработки CRUD запросов, связанных со свойствами.
 */
@RestController
@Slf4j
public class PropertyCrudController {

    @Autowired
    PropertyCrudService propertyCrudService;

    @Autowired
    PropertySearchService propertySearchService;

    @Autowired
    JsonToObjectConverter jsonToObjectConverter;

    /**
     * Метод обработки запроса на добавление или обновления свойства.
     * Если ID совйства == 0, значит добавяем, иначе, редактируем.
     *
     * @param propertyJson JSON ствйоства
     * @return сообщение об обработке
     */
    @PostMapping("/property/send")
    public String sendProperty(@RequestBody String propertyJson) {
        log.info("[POST /property/send/]\tEntered sendProperty() method");
        try {
            PropertyEntity property = jsonToObjectConverter.jsonToProperty(propertyJson);
            if (property.getId() == 0) {
                propertyCrudService.addProperty(property);
                log.info("[POST /property/send/]\tNew property was created");
                return ResponseToJsonConverter.responseToJson("Свойство было успешно добавлено");
            } else {
                propertyCrudService.updateProperty(property);
                log.info("[POST /property/send/]\tProperty was updated, id = " + property.getId());
                return ResponseToJsonConverter.responseToJson("Свойство было успешно обновлено");
            }
        } catch (Exception e) {
            log.info("[POST /property/send/]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на мягкое удаление свойства
     *
     * @param id ID удаляемого свойства
     * @return сообщение об обработке
     */
    @GetMapping("/property/delete/{id}")
    public String softDeletionOfPropertyById(@PathVariable Long id) {
        log.info("[GET /property/delete/" + id + "]\tEntered softDeletionOfPropertyById() method");
        try {
            propertyCrudService.softDeletionById(id);
            log.info("[GET /property/delete/" + id + "]\tSoft deleted property with id: " + id);
            return ResponseToJsonConverter.responseToJson("Свойство помещено в корзину");
        } catch (Exception e) {
            log.info("[GET /property/delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

}
