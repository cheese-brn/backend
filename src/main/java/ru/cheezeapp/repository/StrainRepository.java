package ru.cheezeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.Strain;

public interface StrainRepository extends JpaRepository<Strain, Long> {
}
