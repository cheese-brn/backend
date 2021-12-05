package ru.cheezeapp.service.vid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.dao.VidStrainRepository;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;
import java.util.Optional;

@Service
public class VidSearchService {

    @Autowired
    RodStrainRepository rodStrainRepository;

    @Autowired
    VidStrainRepository vidStrainRepository;

    /**
     * Поиск видов по заданному ID рода
     * @param id ID рода
     * @return список найденных видов
     */
    @Transactional
    public List<VidStrainEntity> findVidsByRodId(Long id) {
        Optional<RodStrainEntity> rodStrainEntity = rodStrainRepository.findById(id);
        if (rodStrainEntity.isPresent())
            return vidStrainRepository.findAllByRodStrain(rodStrainEntity.get());
        else
            throw new RuntimeException("Rod[id = " + id +"] not found in repository");
    }
}
