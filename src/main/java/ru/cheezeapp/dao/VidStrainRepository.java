package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;

/**
 * Репозиторий для доступа к таблице "Вид штамма"
 *
 * @author Pavel Chupikov
 */
public interface VidStrainRepository extends JpaRepository<VidStrainEntity, Long> {

    /**
     * Метод поиска всех неудаленных видов по роду
     *
     * @param rodStrain род
     * @param sort      сортировка по имени
     * @return список найденных видов
     */
    List<VidStrainEntity> findAllByRodStrainAndDeletedIsFalse(RodStrainEntity rodStrain, Sort sort);

    /**
     * Метод поиска всех удаленных видов
     *
     * @param sort сортировка по имени
     * @return список найденных видов
     */
    List<VidStrainEntity> findAllByDeletedIsTrue(Sort sort);

    /**
     * Процедура удаления всех видов из корзины
     */
    void deleteAllByDeletedIsTrue();

    /**
     * Метод поиска всех неудаленных видов
     *
     * @param sort сортировка по имени
     * @return список найденных видов
     */
    List<VidStrainEntity> findAllByDeletedIsFalse(Sort sort);

    /**
     * Метод поиска неудаленных видов по содержанию строки в имени
     *
     * @param name строка для поиска
     * @return список найденных видов
     */
    List<VidStrainEntity> findByNameContainingIgnoreCaseAndDeletedIsFalse(String name);
}
