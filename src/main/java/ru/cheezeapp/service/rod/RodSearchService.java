package ru.cheezeapp.service.rod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.entity.RodStrainEntity;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для методов с операциями поиска, связанных с родами
 */
@Service
public class RodSearchService {

    @Autowired
    RodStrainRepository rodStrainRepository;

    /**
     * Поиск всех родов репозитория
     *
     * @return список родов, отсортированных по наименованию
     */
    @Transactional(readOnly = true)
    public List<RodStrainEntity> findAllNonDeletedRods() {
        return rodStrainRepository.findAllByDeletedIsFalse(Sort.by("name"));
    }

    /**
     * Поиск всех удаленных видов из репозитория
     *
     * @return список всех видов, отсортированных по наименованию
     */
    @Transactional(readOnly = true)
    public List<RodStrainEntity> findAllDeletedRods() {
        return rodStrainRepository.findAllByDeletedIsTrue(Sort.by("name"));
    }

    /**
     * Поиск рода по ID
     *
     * @param id ID рода
     * @return сущность найденного рода
     */
    @Transactional(readOnly = true)
    public RodStrainEntity findById(Long id) {
        Optional<RodStrainEntity> rod = rodStrainRepository.findById(id);
        if (rod.isPresent())
            return rod.get();
        else
            throw new RuntimeException("Rod[id = " + id + "] not found in repository");
    }

    /**
     * Поиск списка родов по заданному частичному имени
     *
     * @param name частичное имя
     * @return список сущностей родов
     */
    @Transactional(readOnly = true)
    public List<RodStrainEntity> findByNameContaining(String name) {
        return rodStrainRepository.findByNameContainingAndDeletedIsFalse(name);
    }
}
