package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.StrainEntity;

public interface StrainRepository extends JpaRepository<StrainEntity, Long> {
}
