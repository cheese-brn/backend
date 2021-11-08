package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.RodStrainEntity;

public interface RodStrainRepository extends JpaRepository<RodStrainEntity, Long> {
}
