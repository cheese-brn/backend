package ru.cheezeapp.service.strain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.dao.StrainRepository;
import ru.cheezeapp.dao.VidStrainRepository;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для методов с операциями поиска, связанных со штаммами
 */
@Service
public class StrainSearchService {

    @Autowired
    private StrainRepository strainRepository;

    @Autowired
    private VidStrainRepository vidStrainRepository;

    @Autowired
    private RodStrainRepository rodStrainRepository;

    /**
     * Метод поиска всех штаммов
     *
     * @return список штаммов, отсортированных по экземпляру
     */
    @Transactional(readOnly = true)
    public List<StrainEntity> findAll() {
        return strainRepository.findAll(Sort.by("exemplar"));
    }

    /**
     * Метод поиска всех неудаленных штаммов
     *
     * @return список штаммов
     */
    @Transactional(readOnly = true)
    public List<StrainEntity> findAllNonDeletedStrains() {
        return strainRepository.findAllByDeletedIsFalse();
    }

    /**
     * Метод поиска всех удаленных штаммов
     *
     * @return список штаммов, отсортированных по экземпляру
     */
    @Transactional(readOnly = true)
    public List<StrainEntity> findAllDeletedStrains() {
        return strainRepository.findAllByDeletedIsTrue(Sort.by("exemplar"));
    }

    /**
     * Метод поиска штамма по ID
     *
     * @param id ID для поиска
     * @return модель штамма
     */
    @Transactional(readOnly = true)
    public StrainEntity findStrainById(Long id) {
        return strainRepository.findById(id).orElse(null);
    }

    /**
     * Поиск штаммов по заданному ID вида.
     *
     * @param id ID вида
     * @return список найденных неудаленных штаммов, отсортированных по экземпляру
     */
    @Transactional(readOnly = true)
    public List<StrainEntity> findStrainsByVidId(Long id) {
        Optional<VidStrainEntity> vid = vidStrainRepository.findById(id);
        if (vid.isPresent())
            return strainRepository.findAllByVidStrainAndDeletedIsFalse(vid.get(), Sort.by("exemplar"));
        else
            throw new RuntimeException("Vid[id = " + id + "] not found in repository");
    }

    /**
     * Метод поиска штамма по имени
     *
     * @param name имя штамма
     * @return Сущность штамма
     */
    @Transactional(readOnly = true)
    public StrainEntity findByName(String name) {
        return strainRepository.findByExemplar(name).orElse(null);
    }

    /**
     * Метод поиска штамма по ID рода
     *
     * @param id ID рода
     * @return список найденных неудаленных штаммов, отсортированных по экземпляру
     */
    @Transactional(readOnly = true)
    public List<StrainEntity> findStrainsByRodId(Long id) {
        Optional<RodStrainEntity> rod = rodStrainRepository.findById(id);
        if(rod.isPresent()) {
            List<StrainEntity> strainEntities = new ArrayList<>();
            for(VidStrainEntity vid : rod.get().getVids())
                strainEntities.addAll(strainRepository.findAllByVidStrainAndDeletedIsFalse(vid, Sort.by("exemplar")));
            strainEntities.sort(Comparator.comparing(StrainEntity::getExemplar));
            return strainEntities;
        }
        else
            throw new RuntimeException("Rod[id = " + id + "] not found in repository");
    }

}
