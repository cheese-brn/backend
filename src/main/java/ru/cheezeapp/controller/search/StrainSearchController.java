package ru.cheezeapp.controller.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.service.strain.StrainSearchService;
import ru.cheezeapp.utils.jsonConverter.CatalogsToJson;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с поиском штаммов.
 */
@RestController
@Slf4j
public class StrainSearchController {

    @Autowired
    private StrainSearchService strainSearchService;

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
     * Метод поиска штаммов по заданному ID рода и формирования JSON
     *
     * @param id ID рода
     * @return JSON списка штаммов
     */
    @GetMapping("/strains/rods/{id}")
    public String getListOfStrainsByRodId(@PathVariable Long id) {
        List<StrainEntity> strainsListByRodId = strainSearchService.findStrainsByRodId(id);
        log.info("[GET /strains/rods/{id}]\tReturn list of strains by ID's rod");
        return CatalogsToJson.strainCatalogToJson(strainsListByRodId);
    }

    @PostMapping("/strains/searchByName")
    public String getListOfStrainsByExamplarContaining(@RequestBody(required = false) String name) {
        if (name == null)
            return getAllStrains();
        List<StrainEntity> strains = strainSearchService.findByExemplarContaining(name);
        log.info("[GET /rods/searchByName]\tReturn list of rods by containing name");
        return CatalogsToJson.strainCatalogToJson(strains);
    }

}
