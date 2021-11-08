package ru.cheezeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.dao.StrainRepository;

import java.util.List;

@RestController
public class FinderController {
    @Autowired
    private StrainRepository repository;

    @GetMapping("/strains")
    public List<StrainEntity> getAllStrains() {
        return repository.findAll();
    }
}
