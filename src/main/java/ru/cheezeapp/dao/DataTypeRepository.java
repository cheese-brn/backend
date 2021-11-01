package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.DataType;

public interface DataTypeRepository extends JpaRepository<DataType, Long> {
}
