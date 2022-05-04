package ru.cheezeapp.service.strain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.StrainRepository;
import ru.cheezeapp.entity.StrainEntity;

import java.util.Optional;

/**
 * Сервис с Crud операциями для штамма
 */
@Service
@Slf4j
public class StrainCrudService {

    @Autowired
    StrainRepository strainRepository;

    /**
     * Процедура добавления штамма в БД. Также добавляем все параметры штамма.
     *
     * @param strain штамм для добавления
     */
    @Transactional
    public void addStrain(StrainEntity strain) {
        log.info("STRAIN CRUD SERVICE\tEntered addStrain() method");
        strainRepository.save(strain);
        log.info("STRAIN CRUD SERVICE\taddStrain() method done");
    }

    /**
     * Процедура мягкого удаления штамма по ID.
     * Помечаем штамм как удаленный и обновляем его в БД.
     *
     * @param id ID штамма для удаления
     */
    @Transactional
    public void softDeletionOfStrainById(Long id) {
        log.info("STRAIN CRUD SERVICE\tEntered softDeletionOfStrainById() method");
        Optional<StrainEntity> strainEntity = strainRepository.findById(id);
        if (strainEntity.isPresent()) {
            strainEntity.get().setDeleted(true);
            strainRepository.save(strainEntity.get());
            log.info("STRAIN CRUD SERVICE\tsoftDeletionOfStrainById() method done");
        } else {
            log.info("STRAIN CRUD SERVICE\tsoftDeletionOfStrainById() method done");
            throw new RuntimeException("Штамм не существует");
        }
    }

    /**
     * Процедура полного удаления штамма из БД по ID.
     * Удаляем как штамм, так и все фактические и функциональные свойства через соответствующие сервисы.
     *
     * @param id ID штамма для удаления
     */
    @Transactional
    public void hardDeletionOfStrainById(Long id) {
        log.info("STRAIN CRUD SERVICE\tEntered hardDeletionOfStrainById() method");
        Optional<StrainEntity> strainEntity = strainRepository.findById(id);
        if (strainEntity.isPresent()) {
            strainRepository.deleteById(id);
            log.info("STRAIN CRUD SERVICE\thardDeletionOfStrainById() method done");
        } else {
            log.info("STRAIN CRUD SERVICE\thardDeletionOfStrainById() method done");
            throw new RuntimeException("Штамм не существует");
        }
    }

    /**
     * Процедура обновления штамма в БД. Также обновляем все параметры штамма.
     *
     * @param strain штамм для обновления
     */
    @Transactional
    public void updateStrain(StrainEntity strain) {
        log.info("STRAIN CRUD SERVICE\tEntered updateStrain() method");
        Optional<StrainEntity> strainEntity = strainRepository.findById(strain.getId());
        if (strainEntity.isPresent()) {
            strainRepository.save(strain);
        }
        log.info("STRAIN CRUD SERVICE\tupdateStrain() method done");
    }

    /**
     * Процедура удаления всех штаммов с пометкой "Удален".
     */
    @Transactional
    public void hardDeleteAll() {
        log.info("STRAIN CRUD SERVICE\tEntered hardDeleteAllStrains() method");
        strainRepository.deleteAllByDeletedIsTrue();
        log.info("STRAIN CRUD SERVICE\thardDeleteAllStrains() method done");
    }

    /**
     * Процедура восстановления штамма из корзины
     *
     * @param id ID штамма для восстановления
     */
    @Transactional
    public void restoreStrain(Long id) {
        log.info("STRAIN CRUD SERVICE\tEntered restoreStrain() method");
        Optional<StrainEntity> strainEntity = strainRepository.findById(id);
        if (strainEntity.isPresent()) {
            StrainEntity strain = strainEntity.get();
            strain.setDeleted(false);
            strainRepository.save(strain);
        }
        log.info("STRAIN CRUD SERVICE\trestoreStrain() method done");
    }

}
