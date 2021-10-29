package ru.cheezeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.Strain;
import ru.cheezeapp.repository.StrainRepository;

import java.util.List;

@RestController
public class FinderController {
    @Autowired
    private StrainRepository repository;

    @GetMapping("/strains")
    public List<Strain> getAllStrains() {
        return repository.findAll();
    }
}
