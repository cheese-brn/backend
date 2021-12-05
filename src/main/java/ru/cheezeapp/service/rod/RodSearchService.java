package ru.cheezeapp.service.rod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.entity.RodStrainEntity;

import java.util.List;

@Service
public class RodSearchService {

    @Autowired
    RodStrainRepository rodStrainRepository;

    /**
     * Поиск рода по ID
     * 
     * @param id ID рода
     * @return сущность найденного рода
     */
    @Transactional
    public RodStrainEntity findRodById(Long id) {
        return rodStrainRepository.findById(id).orElse(null);
    }

    /**
     * Поиск всех родов репозитория
     *
     * @return список родов
     */
    @Transactional
    public List<RodStrainEntity> findAll() {return rodStrainRepository.findAll();}

}
