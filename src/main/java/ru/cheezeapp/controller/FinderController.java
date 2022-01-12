package ru.cheezeapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.service.property.PropertySearchService;
import ru.cheezeapp.service.rod.RodSearchService;
import ru.cheezeapp.service.strain.StrainSearchService;
import ru.cheezeapp.service.subproperty.SubpropertySearchService;
import ru.cheezeapp.service.vid.VidSearchService;
import ru.cheezeapp.utils.jsonConverter.CatalogsToJson;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import java.util.List;

@RestController
@Slf4j
public class FinderController {

    @Autowired
    private RodSearchService rodSearchService;

    @Autowired
    private VidSearchService vidSearchService;

    @Autowired
    private StrainSearchService strainSearchService;

    @Autowired
    private PropertySearchService propertySearchService;

    @Autowired
    private SubpropertySearchService subpropertySearchService;


    /**
     * Метод поиска всех неудаленных штаммов. Формируем JSON и отправляем его
     *
     * @return список штаммов
     */
    @GetMapping("/strains")
    public String getAllStrains() {
        List<StrainEntity> allStrains = strainSearchService.findAllNonDeletedStrains();
        log.info("[GET /strains]\tReturn all non deleted strains");
        return CatalogsToJson.strainCatalogToJson(allStrains);
    }

    /**
     * Метод поиска всех удаленных штаммов. Формируем JSON и отправляем его
     *
     * @return список штаммов
     */
    @GetMapping("/deleted_strains")
    public String getAllDeletedStrains() {
        List<StrainEntity> allStrains = strainSearchService.findAllDeletedStrains();
        log.info("[GET /strains]\tReturn all deleted strains");
        return CatalogsToJson.strainCatalogToJson(allStrains);
    }

    /**
     * Метод поиска штамма по ID. Формируем JSON и отправляем клиенту
     *
     * @param id ID штамма
     * @return JSON штамма
     */
    @GetMapping("/strains/{id}")
    public String getStrainById(@PathVariable Long id) {
        StrainEntity strain = strainSearchService.findStrainById(id);
        log.info("[GET /strains/{id}]\tReturn strain by ID");
        return ObjectToJsonConverter.strainToJson(strain);
    }

    /**
     * Метод поиска штаммов по заданному ID вида и формирования JSON
     *
     * @param id ID вида
     * @return JSON списка штаммов
     */
    @GetMapping("/strains/vids/{id}")
    public String getListOfStrainsByVidId(@PathVariable Long id) {
        List<StrainEntity> strainsListByVidId = strainSearchService.findStrainsByVidId(id);
        log.info("[GET /strains/vids/{id}]\tReturn list of strains by ID's vid");
        return CatalogsToJson.strainCatalogToJson(strainsListByVidId);
    }

    /**
     * Метод поиска всех родов и формирования их в список, вывод в виде JSON
     *
     * @return JSON списка родов
     */
    @GetMapping("/rods")
    public String getListOfRods() {
        List<RodStrainEntity> rodsStrainEntity = rodSearchService.findAll();
        log.info("[GET /rods]\tReturn all rods by list");
        return CatalogsToJson.rodCatalogToJson(rodsStrainEntity);
    }

    //TODO реализовать JSON всех полей рода
    @GetMapping("/rods/{id}")
    public String getRodById(@PathVariable Long id) {
        RodStrainEntity rod = rodSearchService.findById(id);
        return ObjectToJsonConverter.rodToJson(rod);
    }

    /**
     * API-метод, который предоставляет список всех видов в формате JSON
     * @return JSON списка видов
     */
    @GetMapping("/vids")
    public String getListOfVids() {
        List<VidStrainEntity> allVids = vidSearchService.findAll();
        log.info("[GET /vids]\tReturn all vids by list");
        return CatalogsToJson.vidCatalogToJson(allVids);
    }

    //TODO реализовать метод JSON (стоит заглушка)
    @GetMapping("/vids/{id}")
    public String getVidById(@PathVariable Long id) {
        VidStrainEntity vid = vidSearchService.findById(id);
        return ObjectToJsonConverter.vidToJson(vid);
    }

    /**
     * Метод поиска видов по заданному ID рода и формирования JSON
     *
     * @param id ID рода
     * @return JSON списка видов
     */
    @GetMapping("/vids/rods/{id}")
    public String getListOfVidsByRodId(@PathVariable Long id) {
        List<VidStrainEntity> vidListByRodId = vidSearchService.findVidsByRodId(id);
        log.info("[GET /vids/rods/{id}]\tReturn list of vids by ID's rod");
        return CatalogsToJson.vidCatalogToJson(vidListByRodId);
    }

    /**
     * Метод поиск всех свойств и формирования JSON
     *
     * @return JSON списка свойств
     */
    @GetMapping("/properties")
    public String getListOfProperties() {
        List<PropertyEntity> properties = propertySearchService.findAll();
        log.info("[GET /properties]\tReturn list of all properties");
        return CatalogsToJson.propertyCatalogToJson(properties);
    }

    //TODO заглушку в JSON потом убрать
    @GetMapping("/properties/{id}")
    public String getPropertyById(@PathVariable Long id) {
        PropertyEntity property = propertySearchService.findById(id);
        return ObjectToJsonConverter.propertyToJson(property);
    }

    @GetMapping("/subproperties")
    public String getSubpropertyList() {
        List<SubPropertyEntity> subproperties = subpropertySearchService.findAll();
        log.info("[GET /subproperties]\tReturn a list of subproperties");
        return CatalogsToJson.catalogSubpropertyToJson(subproperties);
    }

    //TODO убрать заглушку JSON
    @GetMapping("/subproperties/{id}")
    public String getSubpropertyById(@PathVariable Long id) {
        SubPropertyEntity subproperty = subpropertySearchService.findById(id);
        return ObjectToJsonConverter.subpropertyToJson(subproperty);
    }

    /**
     * Метод поиска подсвойств по заданному ID свойства и формирования JSON
     *
     * @param id ID свойства
     * @return JSON списка подсвойств
     */
    @GetMapping("/subproperties/properties/{id}")
    public String getListOfSubpropertiesByPropertyId(@PathVariable Long id) {
        List<SubPropertyEntity> subproperties = subpropertySearchService.findSubpropertiesByPropertyId(id);
        log.info("[GET /subproperties/properties/{id}]\tReturn list of subproperty by ID's property");
        return CatalogsToJson.catalogSubpropertyToJson(subproperties);
    }

}
