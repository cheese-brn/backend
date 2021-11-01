package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.VidStrain;

public interface VidStrainRepository extends JpaRepository<VidStrain, Long> {
}
