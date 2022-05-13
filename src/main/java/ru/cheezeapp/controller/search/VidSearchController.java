package ru.cheezeapp.controller.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.service.vid.VidSearchService;
import ru.cheezeapp.utils.jsonConverter.CatalogsToJson;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для обработки запросов, связанных с поиском видов.
 *
 * @author Pavel Chupikov
 */
@RestController
@Slf4j
@CrossOrigin
public class VidSearchController {

    @Autowired
    private VidSearchService vidSearchService;

    /**
     * API-метод, который предоставляет список всех неудаленных видов в формате JSON
     *
     * @return JSON списка видов
     * @author Nikolay Golovnev
     */
    @GetMapping("/vids")
    public String getListOfVids() {
        List<VidStrainEntity> allVids = vidSearchService.findAllNonDeletedVids();
        log.info("[GET /vids]\tReturn all non deleted vids by list");
        return CatalogsToJson.vidCatalogToJson(allVids);
    }

    /**
     * API-метод, который предоставляет список всех удаленных видов в формате JSON
     *
     * @return JSON списка видов
     * @author Nikolay Golovnev
     */
    @GetMapping("/deleted_vids")
    public String getListOfDeletedVids() {
        List<VidStrainEntity> allVids = vidSearchService.findAllDeletedVids();
        log.info("[GET /deleted_vids]\tReturn all deleted vids by list");
        return CatalogsToJson.vidCatalogToJson(allVids);
    }

    /**
     * Обробатчик запроса на поиск вида по id и передачи его в виде JSON
     *
     * @param id id вида
     * @return JSON вида
     */
    @GetMapping("/vids/{id}")
    public String getVidById(@PathVariable Long id) {
        VidStrainEntity vid = vidSearchService.findById(id);
        log.info("[GET /vids/" + id + "]\tReturn vid with id " + id);
        return ObjectToJsonConverter.vidToJson(vid);
    }

    /**
     * Обробатчик запроса на поиск видов по заданному ID рода и формирования JSON
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
     * Обробатчик запроса на поиск родов по содержанию строки в их имени
     *
     * @param name строка для поиска
     * @return список родов
     * @author Nikolay Golovnev
     */
    @PostMapping("/vids/searchByName")
    public String getListOfVidsByNameContaining(@RequestBody(required = false) String name) {
        if (name == null)
            return getListOfVids();
        List<VidStrainEntity> vids = vidSearchService.findByNameContaining(name);
        log.info("[POST /vids/searchByName]\tReturn list of vids by containing name");
        List<String> returnValue = new ArrayList<>();
        for (VidStrainEntity vid : vids) {
            returnValue.add(ObjectToJsonConverter.vidToJson(vid));
        }
        return returnValue.toString();
    }

}
