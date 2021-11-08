package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.PropertyEntity;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
}
