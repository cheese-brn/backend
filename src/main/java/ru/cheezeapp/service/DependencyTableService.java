package ru.cheezeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.DependencyTableRepository;
import ru.cheezeapp.entity.DependencyTableEntity;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class DependencyTableService {

    @Autowired
    DependencyTableRepository dependencyTableRepository;

    @Transactional
    public void addFunction(List<SubPropertyEntity> function) {
        dependencyTableRepository.save(DependencyTableEntity.builder()
                .firstSubProperty(function.get(0))
                .secondSubProperty(function.get(1))
                .factParametrsFunc(new ArrayList<>())
                .build());
    }

}
