package ru.cheezeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
