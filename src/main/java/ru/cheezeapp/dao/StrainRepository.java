package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.Strain;

public interface StrainRepository extends JpaRepository<Strain, Long> {
}
