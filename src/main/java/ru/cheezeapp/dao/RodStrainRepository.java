package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.RodStrainEntity;

import java.util.List;
import java.util.Optional;

public interface RodStrainRepository extends JpaRepository<RodStrainEntity, Long> {
    Optional<RodStrainEntity> findById(Long id, Sort name);
    List<RodStrainEntity> findByNameContaining(String name);
}
