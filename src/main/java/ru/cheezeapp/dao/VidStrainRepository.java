package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;

public interface VidStrainRepository extends JpaRepository<VidStrainEntity, Long> {
    List<VidStrainEntity> findAllByRodStrain(RodStrainEntity rodStrain);
    List<VidStrainEntity> findByNameContaining(String name);
}
