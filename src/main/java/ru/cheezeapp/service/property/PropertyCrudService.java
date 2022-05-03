package ru.cheezeapp.service.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.PropertyRepository;
import ru.cheezeapp.dao.SubPropertyRepository;
import ru.cheezeapp.entity.*;

import java.util.List;
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
            List<FactParametrEntity> factParametrEntities = property.getFactParametrs();
            property.setFactParametrs(propertyEntity.get().getFactParametrs());
            property.getFactParametrs().clear();
            property.getFactParametrs().addAll(factParametrEntities);
            List<SubPropertyEntity> subProperties = property.getSubProperties();
            property.setSubProperties(propertyEntity.get().getSubProperties());
            property.getSubProperties().clear();
            property.getSubProperties().addAll(subProperties);
            List<DependencyTableEntity> dependencies = property.getDependencies();
            property.setDependencies(propertyEntity.get().getDependencies());
            property.getDependencies().clear();
            property.getDependencies().addAll(dependencies);
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
        } else {
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
        } else {
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
        } else {
            log.info("PROPERTY CRUD SERVICE\trestoreById() method done with exception");
            throw new RuntimeException("Свойство не существует");
        }
    }

    /**
     * Процедура удаления всех свойств из корзины
     */
    public void hardDeleteAll() {
        log.info("PROPERTY CRUD SERVICE\tEntered hardDeleteAllProperties() method");
        propertyRepository.deleteAllByDeletedIsTrue();
        log.info("PROPERTY CRUD SERVICE\thardDeleteAllProperties() method done");
    }
}
