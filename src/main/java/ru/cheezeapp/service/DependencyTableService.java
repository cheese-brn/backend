package ru.cheezeapp.service;

import com.sun.tools.javac.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.DependencyTableRepository;
import ru.cheezeapp.dao.PropertyRepository;
import ru.cheezeapp.dao.SubPropertyRepository;
import ru.cheezeapp.entity.DependencyTableEntity;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.entity.SubPropertyEntity;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;

import java.util.ArrayList;
import java.util.List;

@Service
public class DependencyTableService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    DependencyTableRepository dependencyTableRepository;

    @Autowired
    SubPropertyRepository subPropertyRepository;

    @Autowired
    JsonToObjectConverter jsonToObjectConverter;

    @Transactional
    public void addFunctions(String propertyJson, PropertyEntity property) {
        List<Pair<List<SubPropertyEntity>, String>> functions = jsonToObjectConverter.jsonToFunction(propertyJson, property);
        if (functions.size() == 0)
            return;
        PropertyEntity propertyEntity = propertyRepository.findByCypher(property.getCypher());
        for (Pair<List<SubPropertyEntity>, String> function : functions)
            dependencyTableRepository.save(DependencyTableEntity.builder()
                    .property(propertyEntity)
                    .firstSubProperty(subPropertyRepository.findByCypher(function.fst.get(0).getCypher()))
                    .secondSubProperty(subPropertyRepository.findByCypher(function.fst.get(1).getCypher()))
                    .functionName(function.snd)
                    .factParametrsFunc(new ArrayList<>())
                    .build());
    }

    @Transactional
    public void deleteAllByPropertyId(Long propertyId) {
        if (propertyRepository.findById(propertyId).isPresent())
            dependencyTableRepository.deleteAllByPropertyId(propertyId);
    }
}
