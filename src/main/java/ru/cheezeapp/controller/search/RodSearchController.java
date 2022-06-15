package ru.cheezeapp.controller.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.service.rod.RodSearchService;
import ru.cheezeapp.utils.jsonConverter.CatalogsToJson;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с поиском родов.
 *
 * @author Pavel Chupikov
 */
@RestController
@Slf4j
public class RodSearchController {

    @Autowired
    private RodSearchService rodSearchService;

    /**
     * Обработчик запроса на поиск всех родов и формирования их в список, вывод в виде JSON
     *
     * @return JSON списка родов
     * @author Nikolay Golovnev
     */
    @GetMapping("/rods")
    public String getListOfRods() {
        List<RodStrainEntity> rodsStrainEntity = rodSearchService.findAllNonDeletedRods();
        log.info("[GET /rods]\tReturn all non deleted rods by list");
        return CatalogsToJson.rodCatalogToJson(rodsStrainEntity);
    }

    /**
     * Обработчик запроса на поиск родов и формирования их в список, вывод в виде JSON
     *
     * @return JSON списка родов
     * @author Nikolay Golovnev
     */
    @GetMapping("/deleted_rods")
    public String getListOfDeletedRods() {
        List<RodStrainEntity> rodsStrainEntity = rodSearchService.findAllDeletedRods();
        log.info("[GET /deleted_rods]\tReturn all deleted rods by list");
        return CatalogsToJson.rodCatalogToJson(rodsStrainEntity);
    }

    /**
     * Обработчик запроса на поиск рода по Id
     *
     * @param id id рода
     * @return найденный род
     */
    @GetMapping("/rods/{id}")
    public String getRodById(@PathVariable Long id) {
        RodStrainEntity rod = rodSearchService.findById(id);
        log.info("[GET /rods/" + id + "]\tReturn rod with id = " + id);
        return ObjectToJsonConverter.rodToJson(rod);
    }

    /**
     * Обработчик запроса на поиск родов по содержанию строки в их имени
     *
     * @param name строка для поиска
     * @return список родов
     * @author Nikolay Golovnev
     */
    @PostMapping("/rods/searchByName")
    public String getListOfRodsByNameContaining(@RequestBody(required = false) String name) {
        if (name == null)
            return getListOfRods();
        List<RodStrainEntity> rods = rodSearchService.findByNameContaining(name);
        log.info("[GET /rods/searchByName]\tReturn list of rods by containing name");
        List<String> returnValue = new ArrayList<>();
        for (RodStrainEntity rod : rods) {
            returnValue.add(ObjectToJsonConverter.rodToJson(rod));
        }
        return returnValue.toString();
    }

    /**
     * Метод обработки запроса на получение всех родов и их видов
     *
     * @return JSON родов и их видов
     */
    @GetMapping("/rods/rodsWithVids")
    public String getListOfRodsWithVids() {
        List<RodStrainEntity> rodStrainEntityList = rodSearchService.findAllNonDeletedRods();
        log.info("[GET /rods/rodsWithVids]\tReturn list of rods with vids");
        return CatalogsToJson.rodsWithVidsToJson(rodStrainEntityList);
    }

}
