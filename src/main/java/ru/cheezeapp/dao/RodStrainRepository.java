package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.RodStrainEntity;

import java.util.List;

/**
 * Репозиторий для доступа к таблице "Род штамма"
 */
public interface RodStrainRepository extends JpaRepository<RodStrainEntity, Long> {

    /**
     * Метод поиска всех неудаленных родов
     *
     * @param sort сортировка по имени
     * @return список неудаленных родов
     */
    List<RodStrainEntity> findAllByDeletedIsFalse(Sort sort);

    /**
     * Метод поиска всех удаленных родов
     *
     * @param sort сортировка по имени
     * @return список удаленных родов
     */
    List<RodStrainEntity> findAllByDeletedIsTrue(Sort sort);

    /**
     * Процедура удаления всех родов из корзины
     */
    void deleteAllByDeletedIsTrue();

    /**
     * Метод поиска неудаленных родов по содержанию строки в имени
     *
     * @param name строка для поиска
     * @return список найденных родов
     */
    List<RodStrainEntity> findByNameContainingIgnoreCaseAndDeletedIsFalse(String name);
}
