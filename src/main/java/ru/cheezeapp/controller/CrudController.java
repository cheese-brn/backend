package ru.cheezeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.model.FactParametr;
import ru.cheezeapp.service.strain.StrainCrudService;

import java.util.List;

/**
 * Crud контроллер для обработки запросов, связанных
 */
@RestController
public class CrudController {

    @Autowired
    StrainCrudService strainCrudService;

    @GetMapping("/strain/delete/{id}")
    public String deleteStrainById(@PathVariable Long id) {
        try {
            strainCrudService.deleteStrainById(id);
            return "good :)";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

}
