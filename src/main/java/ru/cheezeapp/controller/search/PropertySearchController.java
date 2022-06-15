package ru.cheezeapp.controller.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.service.property.PropertySearchService;
import ru.cheezeapp.utils.jsonConverter.CatalogsToJson;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер для обработки запросов, связанных с поиском свойств.
 *
 * @author Pavel Chupikov
 */
@RestController
@Slf4j
@CrossOrigin
public class PropertySearchController {

    @Autowired
    private PropertySearchService propertySearchService;

    /**
     * Обработчик запроса на поиск всех неудаленных свойств и передачи его в виде JSON
     *
     * @return JSON списка свойств
     */
    @GetMapping("/properties")
    public String getListOfProperties() {
        List<PropertyEntity> properties = propertySearchService.findAllNonDeletedProperties();
        log.info("[GET /properties]\tReturn list of all non deleted properties");
        return CatalogsToJson.propertyCatalogToJson(properties);
    }

    /**
     * Обработчик запроса на поиск всех удаленных свойств и передачи его в виде JSON
     *
     * @return JSON списка свойств
     */
    @GetMapping("/deleted_properties")
    public String getListOfDeletedProperties() {
        List<PropertyEntity> properties = propertySearchService.findAllDeletedProperties();
        log.info("[GET /deleted_properties]\tReturn list of all deleted properties");
        return CatalogsToJson.propertyCatalogToJson(properties);
    }

    /**
     * Обработчик запроса на поиск свойства по id и передачи его в виде JSON
     *
     * @param id id свойства
     * @return JSON свойства
     */
    @GetMapping("/properties/{id}")
    public String getPropertyById(@PathVariable Long id) {
        PropertyEntity property = propertySearchService.findById(id);
        log.info("[GET /properties/" + id + "]\tReturn property with id " + id);
        return ObjectToJsonConverter.propertyToJson(property);
    }

    /**
     * Обработчик запроса на поиск свойств по фрагменту
     *
     * @param name фрагмент имени свойства
     * @return список найденных свойств
     */
    @PostMapping("/property/searchByName")
    public String getListOfPropertiesByNameContaining(@RequestBody(required = false) String name) {
        List<PropertyEntity> properties = propertySearchService.findByNameContaining(name);
        log.info("[POST /property/searchByName]\tReturn list of properties by containing name");
        return properties.stream().map(ObjectToJsonConverter::propertyToJson).collect(Collectors.toList()).toString();
    }

}
