package ru.cheezeapp.service.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.PropertyRepository;
import ru.cheezeapp.dao.SubPropertyRepository;
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

    @Autowired
    SubPropertyRepository subPropertyRepository;

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
            property.setFactParametrs(propertyEntity.get().getFactParametrs());
            propertyRepository.save(property);
            log.info("PROPERTY CRUD SERVICE\tUpdated property with id = " + property.getId());
        }
        log.info("PROPERTY CRUD SERVICE\tupdateProperty() method done");
    }

    /**
     * Процедура мягкого удаления свойства по ID.
     * Помечаем свойство как удаленное и обновляем его в БД.
     *
     * @param id id свойства
     */
    @Transactional
    public void softDeletionById(Long id) {
        log.info("PROPERTY CRUD SERVICE\tEntered softDeletionById() method");
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(id);
        if (propertyEntity.isPresent()) {
            propertyEntity.get().setDeleted(true);
            propertyRepository.save(propertyEntity.get());
            log.info("PROPERTY CRUD SERVICE\tsoftDeletionById() method done");
        }
        else {
            log.info("PROPERTY CRUD SERVICE\tsoftDeletionById() method done with exception");
            throw new RuntimeException("Свойство не существует");
        }
    }

    /**
     * Процедура полного удаления свойства по ID.
     * Помечаем свойство как удаленное и обновляем его в БД.
     *
     * @param id id свойства
     */
    @Transactional
    public void hardDeletionById(Long id) {
        log.info("PROPERTY CRUD SERVICE\tEntered hardDeletionById() method");
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(id);
        if (propertyEntity.isPresent()) {
            propertyRepository.deleteById(id);
            log.info("PROPERTY CRUD SERVICE\thardDeletionById() method done");
        }
        else {
            log.info("PROPERTY CRUD SERVICE\thardDeletionById() method done with exception");
            throw new RuntimeException("Свойство не существует");
        }
    }

    /**
     * Процедура восстановления свойства по ID.
     * Помечаем свойство как неудаленное и обновляем его в БД.
     *
     * @param id id свойства
     */
    @Transactional
    public void restoreById(Long id) {
        log.info("PROPERTY CRUD SERVICE\tEntered restoreById() method");
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(id);
        if (propertyEntity.isPresent()) {
            propertyEntity.get().setDeleted(false);
            propertyRepository.save(propertyEntity.get());
            log.info("PROPERTY CRUD SERVICE\trestoreById() method done");
        }
        else {
            log.info("PROPERTY CRUD SERVICE\trestoreById() method done with exception");
            throw new RuntimeException("Свойство не существует");
        }
    }

}
