package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.RodStrainEntity;

import java.util.List;

public interface RodStrainRepository extends JpaRepository<RodStrainEntity, Long> {
    List<RodStrainEntity> findAllByDeletedIsFalse(Sort sort);
    List<RodStrainEntity> findAllByDeletedIsTrue(Sort sort);
    void deleteAllByDeletedIsTrue();
    List<RodStrainEntity> findByNameContainingIgnoreCaseAndDeletedIsFalse(String name);
}
