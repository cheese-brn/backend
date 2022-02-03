package ru.cheezeapp.controller.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.service.property.PropertySearchService;
import ru.cheezeapp.utils.jsonConverter.CatalogsToJson;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с поиском свойств.
 */
@RestController
@Slf4j
public class PropertySearchController {

    @Autowired
    private PropertySearchService propertySearchService;

    /**
     * Метод поиска списка всех свойств и передачи его в виде JSON
     *
     * @return JSON списка свойств
     */
    @GetMapping("/properties")
    public String getListOfProperties() {
        List<PropertyEntity> properties = propertySearchService.findAll();
        log.info("[GET /properties]\tReturn list of all properties");
        return CatalogsToJson.propertyCatalogToJson(properties);
    }

    /**
     * Метод поиска свойства по id и передачи его в виде JSON
     *
     * @param id id свойства
     * @return JSON свойства
     */
    //TODO заглушку в JSON потом убрать
    @GetMapping("/properties/{id}")
    public String getPropertyById(@PathVariable Long id) {
        PropertyEntity property = propertySearchService.findById(id);
        log.info("[GET /properties/" + id + "]\tReturn property with id " + id);
        return ObjectToJsonConverter.propertyToJson(property);
    }

}
