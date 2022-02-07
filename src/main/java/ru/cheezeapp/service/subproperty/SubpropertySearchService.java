package ru.cheezeapp.service.subproperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.PropertyRepository;
import ru.cheezeapp.dao.SubPropertyRepository;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для методов с операциями поиска, связанных с подсвойствами
 */
@Service
public class SubpropertySearchService {

    @Autowired
    private SubPropertyRepository subPropertyRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Метод поиска подсвойств по ID свойства
     *
     * @param id ID свойства
     * @return список найденных подсвойств, отсортированных по наименованию
     */
    @Transactional(readOnly = true)
    public List<SubPropertyEntity> findSubpropertiesByPropertyId(Long id) {
        Optional<PropertyEntity> property = propertyRepository.findById(id);
        if (property.isPresent())
            return subPropertyRepository.findAllByProperty(property.get(), Sort.by("name"));
        else
            throw new RuntimeException("Property[id = " + id +"] not found in repository");
    }

    /**
     * Метод поиска всех подсвойств
     *
     * @return список всех подсвойств, отсортированных по наименованию
     */
    @Transactional(readOnly = true)
    public List<SubPropertyEntity> findAll() {
        return subPropertyRepository.findAll(Sort.by("name"));
    }

    /**
     * Метод поиска подсвойства по ID
     *
     * @param id ID подсвойства
     * @return сущность подсвойства
     */
    @Transactional(readOnly = true)
    public SubPropertyEntity findById(Long id) {
        Optional<SubPropertyEntity> subProperty = subPropertyRepository.findById(id);
        if (subProperty.isPresent())
            return subProperty.get();
        else
            throw new RuntimeException("Subproperty[id = ]" + id + "] not found in repository");
    }
}
