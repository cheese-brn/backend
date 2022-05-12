package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для доступа к таблице "Штамм"
 *
 * @author Pavel Chupikov
 */
public interface StrainRepository extends JpaRepository<StrainEntity, Long> {

    /**
     * Метод поиска всех штаммов по виду
     *
     * @param vidStrain        вид
     * @param sortingParameter параметр сортировки
     * @return список штаммов
     */
    List<StrainEntity> findAllByVidStrainAndDeletedIsFalse(VidStrainEntity vidStrain, Sort sortingParameter);

    /**
     * Метод поиска всех удаленных штаммов
     *
     * @param sortingParameter параметр сортировки
     * @return список удаленных штаммов
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

    /**
     * Метод поиска неудаленных штаммов, которые содержат в строке экземпляра переданную строку
     *
     * @param name строка для поиска
     * @return список найденных штаммов
     */
    List<StrainEntity> findByExemplarContainingAndDeletedIsFalse(String name);

    /**
     * Метод поиска штамма по id вида, экземпляру и модификации
     *
     * @param vidId        id вида
     * @param exemplar     экземпляр
     * @param modification модификация
     * @return optional объект, содержащий id
     */
    Optional<StrainEntity>
    findStrainEntityByVidStrain_Id_AndExemplarAndModification(Long vidId, String exemplar, String modification);

}
