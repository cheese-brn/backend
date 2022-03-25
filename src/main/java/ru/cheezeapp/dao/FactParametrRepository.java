package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.FactParametrEntity;

import java.util.List;

public interface FactParametrRepository extends JpaRepository<FactParametrEntity, Long> {

    List<FactParametrEntity> findFactParametrEntitiesBySubPropertyId(Long id);

}
