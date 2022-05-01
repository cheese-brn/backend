package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.DataTypeEntity;

/**
 * Репозиторий для доступа к таблице "Тип данных"
 */
public interface DataTypeRepository extends JpaRepository<DataTypeEntity, Long> {
}
