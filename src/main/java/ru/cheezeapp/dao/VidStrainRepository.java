package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.VidStrainEntity;

public interface VidStrainRepository extends JpaRepository<VidStrainEntity, Long> {
}
