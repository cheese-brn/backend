package ru.cheezeapp.service.strain;

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
public class StrainCrudService {

    @Autowired
    StrainRepository strainRepository;

    @Transactional
    public void addStrain(StrainEntity entity) {
        strainRepository.save(entity);
    }

    /**
     * Метод удаления штамма по ID
     *
     * @param id ID штамма для удаления
     */
    @Transactional
    public void deleteStrainById(Long id) {
        Optional<StrainEntity> strainEntity = strainRepository.findById(id);
        if (strainEntity.isPresent())
            strainRepository.deleteById(id);
    }

}
