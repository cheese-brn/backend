package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;
import java.util.Optional;

public interface VidStrainRepository extends JpaRepository<VidStrainEntity, Long> {
    List<VidStrainEntity> findAllByRodStrain(RodStrainEntity rodStrain);
}
