package ru.cheezeapp.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.PropertyEntity;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.Collection;
import java.util.List;

public interface SubPropertyRepository extends JpaRepository<SubPropertyEntity, Long> {
    List<SubPropertyEntity> findAllByProperty(PropertyEntity propertyEntity, Sort name);
    List<SubPropertyEntity> findByCypher(Integer cypher);

    Collection<? extends SubPropertyEntity> findAllByProperty_Id(Long id);
}
