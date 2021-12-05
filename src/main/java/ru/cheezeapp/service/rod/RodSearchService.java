package ru.cheezeapp.service.rod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.entity.RodStrainEntity;

@Service
public class RodSearchService {

    @Autowired
    RodStrainRepository rodStrainRepository;

    @Transactional
    public RodStrainEntity findRodById(Long id) {
        return rodStrainRepository.findById(id).orElse(null);
    }

}
