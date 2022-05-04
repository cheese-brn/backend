package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.DependencyTableEntity;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.Optional;

/**
 * Репозиторий для доступа к таблице "Зависимости"
 */
public interface DependencyTableRepository extends JpaRepository<DependencyTableEntity, Long> {

    /**
     * Метод поиска зависимости по первому и второму подсвойствам
     *
     * @param first  первое подсвойство
     * @param second второе подсвойство
     * @return optional зависимости
     */
    Optional<DependencyTableEntity> findByFirstSubPropertyAndSecondSubProperty(SubPropertyEntity first, SubPropertyEntity second);

    /**
     * Метод поиска зависимости по id первого подсвойства
     *
     * @param id id первого подсвойства
     * @return optional зависимости
     */
    Optional<DependencyTableEntity> findByFirstSubProperty_Id(Long id);

}
