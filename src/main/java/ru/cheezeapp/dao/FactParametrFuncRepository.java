package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.FactParametrFuncEntity;

/**
 * Репозиторий для доступа к таблице "Фактические параметры функции"
 */
public interface FactParametrFuncRepository extends JpaRepository<FactParametrFuncEntity, Long> {
}
