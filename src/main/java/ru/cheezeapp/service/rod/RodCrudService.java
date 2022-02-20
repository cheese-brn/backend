package ru.cheezeapp.service.rod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.dao.VidStrainRepository;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.Optional;

/**
 * Сервис с Crud операциями для рода
 */
@Service
@Slf4j
public class RodCrudService {

    @Autowired
    RodStrainRepository rodStrainRepository;

    @Autowired
    VidStrainRepository vidStrainRepository;

    /**
     * Процедура для добавления нового рода в БД.
     *
     * @param rodStrainEntity род для добавления
     */
    @Transactional
    public void addRod(RodStrainEntity rodStrainEntity) {
        log.info("ROD CRUD SERVICE\tEntered addRod() method");
        rodStrainRepository.save(rodStrainEntity);
        log.info("ROD CRUD SERVICE\taddRod() method done");
    }

    /**
     * Процедура обновления рода в БД.
     *
     * @param rodStrainEntity род для обновления
     */
    @Transactional
    public void updateRod(RodStrainEntity rodStrainEntity) {
        log.info("ROD CRUD SERVICE\tEntered updateRod() method");
        Optional<RodStrainEntity> rodOptional = rodStrainRepository.findById(rodStrainEntity.getId());
        if (rodOptional.isPresent()) {
            rodStrainEntity.setVids(rodOptional.get().getVids());
            rodStrainRepository.save(rodStrainEntity);
        }
        log.info("ROD CRUD SERVICE\tupdateRod() method done");
    }

    /**
     * Процедура мягкого удаления рода по ID.
     * Помечаем род как удаленный и обновляем его в БД. Также помечаем удаленными все виды рода.
     *
     * @param id ID рода для удаления
     */
    @Transactional
    public void softDeletionById(Long id) {
        log.info("ROD CRUD SERVICE\tEntered softDeletionById() method");
        Optional<RodStrainEntity> rodStrainEntity = rodStrainRepository.findById(id);
        if (rodStrainEntity.isPresent()) {
            rodStrainEntity.get().setDeleted(true);
            for (VidStrainEntity vid : rodStrainEntity.get().getVids()) {
                vid.setDeleted(true);
                vidStrainRepository.save(vid);
            }
            rodStrainRepository.save(rodStrainEntity.get());
            log.info("ROD CRUD SERVICE\tsoftDeletionById() method done");
        } else {
            log.info("ROD CRUD SERVICE\tsoftDeletionById() method done with exception");
            throw new RuntimeException("Род не существует");
        }
    }

    /**
     * Процедура полного удаления рода из БД по ID. Вместе с родом удаляются виды и штаммы данного рода
     *
     * @param id ID рода для удаления
     */
    @Transactional
    public void hardDeletionById(Long id) {
        log.info("ROD CRUD SERVICE\tEntered hardDeletionById() method");
        Optional<RodStrainEntity> rodStrainEntity = rodStrainRepository.findById(id);
        if (rodStrainEntity.isPresent()) {
            rodStrainRepository.deleteById(id);
            log.info("ROD CRUD SERVICE\thardDeletionById() method done");
        } else {
            log.info("ROD CRUD SERVICE\thardDeletionById() method done with exception");
            throw new RuntimeException("Род не существует");
        }
    }

    /**
     * Процедура удаления всех родов в корзине из БД.
     */
    @Transactional
    public void hardDeleteAll() {
        log.info("ROD CRUD SERVICE\tEntered hardDeletionById() method");
        rodStrainRepository.deleteAllByDeletedIsTrue();
        log.info("ROD CRUD SERVICE\thardDeletionById() method done");
    }

    /**
     * Процедура восстановления рода по ID.
     *
     * @param id ID рода для восстановления
     */
    @Transactional
    public void restoreById(Long id) {
        log.info("ROD CRUD SERVICE\tEntered restoreById() method");
        Optional<RodStrainEntity> rodStrainEntity = rodStrainRepository.findById(id);
        if (rodStrainEntity.isPresent()) {
            rodStrainEntity.get().setDeleted(false);
            for (VidStrainEntity vid : rodStrainEntity.get().getVids()) {
                vid.setDeleted(false);
                vidStrainRepository.save(vid);
            }
            rodStrainRepository.save(rodStrainEntity.get());
            log.info("ROD CRUD SERVICE\trestoreById() method done");
        } else {
            log.info("ROD CRUD SERVICE\trestoreById() method done with exception");
            throw new RuntimeException("Род не существует");
        }
    }

}
