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
import ru.cheezeapp.utils.ObjectToJsonConverter;

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
     * Метод поиска всех штаммов. Формируем JSON и отправляем его
     *
     * @return список штаммов
     */
    @GetMapping("/strains")
    public String getAllStrains() {
        List<StrainEntity> allStrains = strainSearchService.findAll();
        log.info("[/strains]\tGET request: return all strains");
        return ObjectToJsonConverter.strainListToJson(allStrains);
    }

    /**
     * Метод поиска штамма по ID. Формируем JSON и отправляем клиенту
     *
     * @param id ID штамма
     * @return JSON штамма
     */
    @GetMapping("/strain/{id}")
    public String getStrainById(@PathVariable Long id) {
        StrainEntity strain = strainSearchService.findStrainById(id);
        log.info("[/strain/id]\tGET request: return strain by ID");
        return ObjectToJsonConverter.strainToJson(strain);
    }

    /**
     * Метод поиска всех родов и формирования их в список, вывод в виде JSON
     *
     * @return JSON списка родов
     */
    @GetMapping("/rod/list")
    public String getListOfRods() {
        List<RodStrainEntity> rodsStrainEntity = rodSearchService.findAll();
        log.info("[/rod/list]\tGET request: return all rods by list");
        return ObjectToJsonConverter.rodListToJson(rodsStrainEntity);
    }

    /**
     * Метод поиска видов по заданному ID рода и формирования JSON
     *
     * @param id ID рода
     * @return JSON списка видов
     */
    @GetMapping("/vid/list/{id}")
    public String getListOfVidsByRodId(@PathVariable Long id) {
        List<VidStrainEntity> vidListByRodId = vidSearchService.findVidsByRodId(id);
        log.info("[/vid/list/id]\tGET request: return list of vids by ID's rod");
        return ObjectToJsonConverter.vidListToJson(vidListByRodId);
    }

    /**
     * Метод поиска штаммов по заданному ID вида и формирования JSON
     *
     * @param id ID вида
     * @return JSON списка штаммов
     */
    @GetMapping("/strain/list/{id}")
    public String getListOfStrainsByVidId(@PathVariable Long id) {
        List<StrainEntity> strainsListByVidId = strainSearchService.findStrainsByVidId(id);
        log.info("[/strain/list/id]\tGET request: return list of strains by ID's vid");
        return ObjectToJsonConverter.strainListToJson(strainsListByVidId);
    }

    /**
     * Метод поиск всех свойств и формирования JSON
     *
     * @return JSON списка свойств
     */
    @GetMapping("/property/list")
    public String getListOfProperties() {
        List<PropertyEntity> properties = propertySearchService.findAll();
        log.info("[/property/list]\tGET request: return list of all properties");
        return ObjectToJsonConverter.propertyListToJson(properties);
    }

    /**
     * Метод поиска подсвойств по заданному ID свойства и формирования JSON
     *
     * @param id ID свойства
     * @return JSON списка подсвойств
     */
    @GetMapping("/subproperty/list/{id}")
    public String getListOfSubpropertiesByPropertyId(@PathVariable Long id) {
        List<SubPropertyEntity> subproperties = subpropertySearchService.findSubpropertiesByPropertyId(id);
        log.info("[/subproperty/list/id]\tGET request: return list of subproperty by ID's property");
        return ObjectToJsonConverter.subpropertyListToJson(subproperties);
    }

}
