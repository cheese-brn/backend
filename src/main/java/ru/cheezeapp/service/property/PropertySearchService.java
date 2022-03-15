package ru.cheezeapp.service.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.PropertyRepository;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.entity.RodStrainEntity;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для методов с операциями поиска, связанных со свойствами
 */
@Service
public class PropertySearchService {

    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Поиск всех свойств в репозитории
     *
     * @return список свойств, отсортированных по наименованию
     */
    @Transactional(readOnly = true)
    public List<PropertyEntity> findAll() {
        return propertyRepository.findAll(Sort.by("name"));
    }

    /**
     * Поиск всех неудаленных свойств в репозитории
     *
     * @return список свойств, отсортированных по наименованию
     */
    @Transactional(readOnly = true)
    public List<PropertyEntity> findAllNonDeletedProperties() {
        return propertyRepository.findAllByDeletedIsFalse(Sort.by("name"));
    }

    /**
     * Поиск всех удаленных свойств в репозитории
     *
     * @return список свойств, отсортированных по наименованию
     */
    @Transactional(readOnly = true)
    public List<PropertyEntity> findAllDeletedProperties() {
        return propertyRepository.findAllByDeletedIsTrue(Sort.by("name"));
    }

    /**
     * Поиск свойства по ID
     *
     * @param id ID свойства
     * @return сущность свойства
     */
    @Transactional(readOnly = true)
    public PropertyEntity findById(Long id) {
        Optional<PropertyEntity> property = propertyRepository.findById(id);
        if (property.isPresent())
            return property.get();
        else
            throw new RuntimeException("Property[id = " + id + "] not found in repository");
    }

    /**
     * Поиск списка свойств по заданному частичному имени
     *
     * @param name частичное имя
     * @return список сущностей свойств
     */
    @Transactional(readOnly = true)
    public List<PropertyEntity> findByNameContaining(String name) {
        if(name.length() == 0)
            return propertyRepository.findAllByDeletedIsFalse(Sort.by("name"));
        return propertyRepository.findByNameContainingIgnoreCaseAndDeletedIsFalse(name);
    }

}
