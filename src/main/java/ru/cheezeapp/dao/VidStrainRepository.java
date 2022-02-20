package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;

public interface VidStrainRepository extends JpaRepository<VidStrainEntity, Long> {
    List<VidStrainEntity> findAllByRodStrainAndDeletedIsFalse(RodStrainEntity rodStrain, Sort sort);
    List<VidStrainEntity> findAllByDeletedIsTrue(Sort sort);
    void deleteAllByDeletedIsTrue();
    List<VidStrainEntity> findAllByDeletedIsFalse(Sort sort);
    List<VidStrainEntity> findByNameContainingAndDeletedIsFalse(String name);
}
