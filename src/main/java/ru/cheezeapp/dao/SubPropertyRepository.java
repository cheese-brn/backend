package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.SubPropertyEntity;

public interface SubPropertyRepository extends JpaRepository<SubPropertyEntity, Long> {
}
