package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.List;

public interface StrainRepository extends JpaRepository<StrainEntity, Long> {
    List<StrainEntity> findAllByVidStrain(VidStrainEntity vidStrain);
}
