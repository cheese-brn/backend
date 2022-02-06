package ru.cheezeapp.service.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.PropertyRepository;
import ru.cheezeapp.entity.PropertyEntity;

import java.util.Optional;

/**
 * Сервис с Crud операциями для свойства
 */
@Service
@Slf4j
public class PropertyCrudService {

    @Autowired
    PropertyRepository propertyRepository;

    /**
     * Процедура добавления свойства. Вместе со свойством добавляем подсвойства.
     *
     * @param property свойство для добавления
     */
    @Transactional
    public void addProperty(PropertyEntity property) {
        log.info("PROPERTY CRUD SERVICE\tEntered addProperty() method");
        propertyRepository.save(property);
        log.info("PROPERTY CRUD SERVICE\taddProperty() method done");
    }

    /**
     * Процедура обновления свойства. Также обновляются все подсвойства.
     *
     * @param property свойство для обновления
     */
    @Transactional
    public void updateProperty(PropertyEntity property) {
        log.info("PROPERTY CRUD SERVICE\tEntered updateProperty() method");
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(property.getId());
        if (propertyEntity.isPresent()) {
            propertyRepository.save(property);
            log.info("PROPERTY CRUD SERVICE\tUpdated property with id = " + property.getId());
        }
        log.info("PROPERTY CRUD SERVICE\tupdateProperty() method done");
    }

}
