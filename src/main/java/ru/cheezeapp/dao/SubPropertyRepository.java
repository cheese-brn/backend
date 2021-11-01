package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.SubProperty;

public interface SubPropertyRepository extends JpaRepository<SubProperty, Long> {
}
