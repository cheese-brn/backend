package ru.cheezeapp.service.strain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.StrainRepository;
import ru.cheezeapp.dao.VidStrainRepository;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;
import java.util.Optional;

/**
 * Сервис над штаммом для операций, связанных с поиском
 */
@Service
public class StrainSearchService {

    @Autowired
    private StrainRepository strainRepository;

    @Autowired
    private VidStrainRepository vidStrainRepository;

    /**
     * Метод поиска всех штаммов
     *
     * @return список штаммов
     */
    @Transactional
    public List<StrainEntity> findAll() {
        return strainRepository.findAll();
    }

    /**
     * Метод поиска всех неудаленных штаммов
     *
     * @return список штаммов
     */
    @Transactional
    public List<StrainEntity> findAllNonDeletedStrains() {
        return strainRepository.findAllByDeletedIsFalse();
    }

    /**
     * Метод поиска всех удаленных штаммов
     *
     * @return список штаммов
     */
    @Transactional
    public List<StrainEntity> findAllDeletedStrains() {
        return strainRepository.findAllByDeletedIsTrue();
    }

    /**
     * Метод поиска штамма по ID
     *
     * @param id ID для поиска
     * @return модель штамма
     */
    @Transactional
    public StrainEntity findStrainById(Long id) {
        return strainRepository.findById(id).orElse(null);
    }

    /**
     * Поиск штаммов по заданному ID вида
     *
     * @param id ID вида
     * @return список найденных штаммов
     */
    @Transactional
    public List<StrainEntity> findStrainsByVidId(Long id) {
        Optional<VidStrainEntity> vid = vidStrainRepository.findById(id);
        if (vid.isPresent())
            return strainRepository.findAllByVidStrain(vid.get());
        else
            throw new RuntimeException("Vid[id = " + id + "] not found in repository");
    }

    public StrainEntity findByName(String name) {
        return strainRepository.findByExemplar(name).orElse(null);
    }

}
