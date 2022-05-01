package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.List;

/**
 * Репозиторий для доступа к таблице "Подсвойство"
 */
public interface SubPropertyRepository extends JpaRepository<SubPropertyEntity, Long> {

    /**
     * Метод поиска подсвойств по свойству
     *
     * @param propertyEntity свойство
     * @param name           сортировка по имени
     * @return список свойств
     */
    List<SubPropertyEntity> findAllByProperty(PropertyEntity propertyEntity, Sort name);

}
