package ru.cheezeapp.service.strain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cheezeapp.dao.StrainRepository;
import ru.cheezeapp.entity.StrainEntity;

import java.util.List;

/**
 * Сервис над штаммом для операций, связанных с поиском
 */
@Service
public class StrainSearchService {

    @Autowired
    private StrainRepository strainRepository;

    /**
     * Метод поиска всех штаммов
     *
     * @return список штаммов
     */
    public List<StrainEntity> findAll() {
        return strainRepository.findAll();
    }

    /**
     * Метод поиска штамма по ID
     *
     * @param id ID для поиска
     * @return модель штамма
     */
    public StrainEntity findStrainById(Long id) {
        return strainRepository.findById(id).orElse(null);
    }

}
