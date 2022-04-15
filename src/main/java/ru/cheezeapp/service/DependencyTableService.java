package ru.cheezeapp.service;

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
import ru.cheezeapp.utils.structures.FunctionPair;

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
        List<FunctionPair> functions = jsonToObjectConverter.jsonToFunction(propertyJson, property);
        if (functions.size() == 0)
            return;
        PropertyEntity propertyEntity = propertyRepository.findByCypher(property.getCypher());
        for (FunctionPair function : functions) {
            List<SubPropertyEntity> subPropertyEntities = subPropertyRepository
                    .findByCypher(function.fst().get(0).getCypher());
            SubPropertyEntity first, second;
            if (subPropertyEntities.isEmpty())
                return;
            if (subPropertyEntities.size() == 1) {
                first = subPropertyEntities.get(0);
                second = subPropertyRepository.findByCypher(function.fst().get(1).getCypher()).get(0);
            }
            else {
                first = subPropertyRepository.findByCypher(function.fst().get(1).getCypher()).get(0);
                second = subPropertyRepository.findByCypher(function.fst().get(1).getCypher()).get(1);
            }
            dependencyTableRepository.save(DependencyTableEntity.builder()
                    .property(propertyEntity)
                    .firstSubProperty(first)
                    .secondSubProperty(second)
                    .functionName(function.snd())
                    .factParametrsFunc(new ArrayList<>())
                    .build());
        }
    }

    @Transactional
    public void deleteAllByPropertyId(Long propertyId) {
        if (propertyRepository.findById(propertyId).isPresent())
            dependencyTableRepository.deleteAllByPropertyId(propertyId);
    }
}
