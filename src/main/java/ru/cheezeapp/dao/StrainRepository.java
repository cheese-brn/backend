package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;
import java.util.Optional;

public interface StrainRepository extends JpaRepository<StrainEntity, Long> {

    /**
     * Метод поиска всех штаммов по виду
     *
     * @param vidStrain вид
     * @param sortingParameter параметр сортировки
     * @return список штаммов
     */
    List<StrainEntity> findAllByVidStrainAndDeletedIsFalse(VidStrainEntity vidStrain, Sort sortingParameter);

    /**
     * Метод поиска всех удаленных штаммов
     *
     * @return список удаленных штаммов
     * @param sortingParameter параметр сортировки
     */
    List<StrainEntity> findAllByDeletedIsTrue(Sort sortingParameter);

    /**
     * Метод поиска всех неудаленных штаммов
     *
     * @return список неудаленных штаммов
     */
    List<StrainEntity> findAllByDeletedIsFalse();

    /**
     * Метод поиска штамма по экземпляру
     *
     * @param exemplar экземпляр
     * @return optional штамма
     */
    Optional<StrainEntity> findByExemplar(String exemplar);

    /**
     * Процедура удаления всех штаммов с пометкой "Удален"
     */
    void deleteAllByDeletedIsTrue();

}
