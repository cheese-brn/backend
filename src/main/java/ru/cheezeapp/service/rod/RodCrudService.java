package ru.cheezeapp.service.rod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.entity.RodStrainEntity;

import java.util.Optional;

/**
 * Сервис с Crud операциями для рода
 */
@Service
@Slf4j
public class RodCrudService {

    @Autowired
    RodStrainRepository rodStrainRepository;

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

}
