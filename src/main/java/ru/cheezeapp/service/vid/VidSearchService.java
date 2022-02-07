package ru.cheezeapp.service.vid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.dao.VidStrainRepository;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для методов с операциями поиска, связанных с видами
 */
@Service
public class VidSearchService {

    @Autowired
    RodStrainRepository rodStrainRepository;

    @Autowired
    VidStrainRepository vidStrainRepository;

    /**
     * Поиск видов по заданному ID рода
     *
     * @param id ID рода
     * @return список найденных видов, отсортированных по наименованию
     */
    @Transactional(readOnly = true)
    public List<VidStrainEntity> findVidsByRodId(Long id) {
        Optional<RodStrainEntity> rodStrainEntity = rodStrainRepository.findById(id, Sort.by("name"));
        if (rodStrainEntity.isPresent())
            return vidStrainRepository.findAllByRodStrain(rodStrainEntity.get());
        else
            throw new RuntimeException("Rod[id = " + id +"] not found in repository");
    }

    /**
     * Поиск всех видов из репозитория
     * @return список всех видов, отсортированных по наименованию
     */
    @Transactional(readOnly = true)
    public List<VidStrainEntity> findAll() {
        return vidStrainRepository.findAll(Sort.by("name"));
    }

    /**
     * Метод поиска вида по ID
     *
     * @param id ID вида
     * @return Сущность вида
     */
    @Transactional(readOnly = true)
    public VidStrainEntity findById(Long id) {
        Optional<VidStrainEntity> vid = vidStrainRepository.findById(id);
        if (vid.isPresent())
            return vid.get();
        else
            throw new RuntimeException("Vid[id = " + id +"] not found in repository");
    }
}
