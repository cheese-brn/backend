package ru.cheezeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.service.strain.StrainSearchService;
import ru.cheezeapp.utils.ObjectToJsonConverter;

import java.util.HashSet;
import java.util.Set;

@RestController
public class FinderController {

    @Autowired
    StrainSearchService strainSearchService;

    /**
     * Метод поиска штамма по ID. Формируем JSON и отправляем клиенту
     *
     * @param id ID штамма
     * @return JSON штамма
     */
    @GetMapping("/strain/{id}")
    public String getStrainById(@PathVariable Long id) {
        StrainEntity strain = strainSearchService.findStrainById(id);
        return ObjectToJsonConverter.strainToJson(strain);
    }

}
