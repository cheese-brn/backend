package ru.cheezeapp.service.subproperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.PropertyRepository;
import ru.cheezeapp.dao.SubPropertyRepository;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.List;
import java.util.Optional;

@Service
public class SubpropertySearchService {
    @Autowired
    private SubPropertyRepository subPropertyRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Transactional
    public List<SubPropertyEntity> findSubpropertiesByPropertyId(Long id) {
        Optional<PropertyEntity> property = propertyRepository.findById(id);
        if (property.isPresent())
            return subPropertyRepository.findAllByProperty(property.get());
        else
            throw new RuntimeException("Property[id = " + id +"] not found in repository");
    }
}
