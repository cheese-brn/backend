package ru.cheezeapp.controller.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.service.property.PropertyCrudService;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;

/**
 * Контроллер для обработки CRUD запросов, связанных со свойствами.
 */
@RestController
@Slf4j
public class PropertyCrudController {

    @Autowired
    PropertyCrudService propertyCrudService;

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
                return "Свойство было успешно добавлено";
            } else {
                propertyCrudService.updateProperty(property);
                log.info("[POST /property/send/]\tProperty was updated, id = " + property.getId());
                return "Свойство было успешно обновлено";
            }
        } catch (Exception e) {
            log.info("[POST /property/send/]\tThrows exception: " + e.getMessage());
            return e.getMessage();
        }
    }

}
