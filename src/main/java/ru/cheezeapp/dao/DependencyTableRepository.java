package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.DependencyTableEntity;

public interface DependencyTableRepository extends JpaRepository<DependencyTableEntity, Long> {
}
