package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.DependencyTable;

public interface DependencyTableRepository extends JpaRepository<DependencyTable, Long> {
}