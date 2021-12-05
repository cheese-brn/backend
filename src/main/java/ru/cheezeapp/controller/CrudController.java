package ru.cheezeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.service.strain.StrainCrudService;

/**
 * Crud контроллер для обработки запросов, связанных
 */
@RestController
public class CrudController {

    @Autowired
    StrainCrudService strainCrudService;



}
