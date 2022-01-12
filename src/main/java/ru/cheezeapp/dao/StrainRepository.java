package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;

public interface StrainRepository extends JpaRepository<StrainEntity, Long> {

    /**
     * Метод поиска всех штаммов по виду
     *
     * @param vidStrain вид
     * @return список штаммов
     */
    List<StrainEntity> findAllByVidStrain(VidStrainEntity vidStrain);

    /**
     * Метод поиска всех удаленных штаммов
     *
     * @return список удаленных штаммов
     */
    List<StrainEntity> findAllByDeletedIsTrue();

    /**
     * Метод поиска всех неудаленных штаммов
     *
     * @return список неудаленных штаммов
     */
    List<StrainEntity> findAllByDeletedIsFalse();
}
