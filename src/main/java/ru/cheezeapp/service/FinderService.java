package ru.cheezeapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.entity.RodStrainEntity;

import java.util.Optional;

@Service
@Slf4j
public class FinderService {
    private final RodStrainRepository rodRepository;

    public FinderService(RodStrainRepository rodRepository) {
        this.rodRepository = rodRepository;
    }

    public RodStrainEntity findRodById(Long id) {
        Optional<RodStrainEntity> rod = rodRepository.findById(id);
        if (rod.isPresent())
            return rod.get();
        else
            throw new RuntimeException("RodEntity[id = " + id + "] not exists");
    }
}
