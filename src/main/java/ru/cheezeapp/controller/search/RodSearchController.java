package ru.cheezeapp.controller.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.service.rod.RodSearchService;
import ru.cheezeapp.utils.jsonConverter.CatalogsToJson;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import java.util.List;

@RestController
@Slf4j
public class RodSearchController {

    @Autowired
    private RodSearchService rodSearchService;

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

}
