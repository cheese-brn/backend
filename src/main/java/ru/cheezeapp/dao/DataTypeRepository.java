package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.DataTypeEntity;

public interface DataTypeRepository extends JpaRepository<DataTypeEntity, Long> {
}
