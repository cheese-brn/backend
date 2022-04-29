package ru.cheezeapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cheezeapp.entity.FactParametrFuncEntity;

import java.util.List;

public interface FactParametrFuncRepository extends JpaRepository<FactParametrFuncEntity, Long> {
    List<FactParametrFuncEntity> findAllByDependencyTable_PropertyId(Long id);

    List<FactParametrFuncEntity> findAllByDependencyTableId(Long id);

}
