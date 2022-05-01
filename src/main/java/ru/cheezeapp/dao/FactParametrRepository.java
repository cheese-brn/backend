package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.FactParametrEntity;

import java.util.List;

/**
 * Репозиторий для доступа к таблице "Фактические параметры"
 */
public interface FactParametrRepository extends JpaRepository<FactParametrEntity, Long> {

    /**
     * Метод поиска фактических параметров по id подсвойства
     *
     * @param id id подсвойства
     * @return список фактических параметров
     */
    List<FactParametrEntity> findFactParametrEntitiesBySubProperty_Id(Long id);

}
