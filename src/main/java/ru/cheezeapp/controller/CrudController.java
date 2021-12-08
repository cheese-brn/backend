package ru.cheezeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.service.strain.StrainCrudService;

import java.util.List;

/**
 * Crud контроллер для обработки запросов, связанных
 */
@RestController
public class CrudController {

    @Autowired
    StrainCrudService strainCrudService;

    @PostMapping("/strain/delete/{id}")
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
