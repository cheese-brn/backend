package ru.cheezeapp.service.factParamFunc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.FactParametrFuncRepository;
import ru.cheezeapp.entity.FactParametrFuncEntity;

import java.util.List;

@Service
public class FactParamFuncService {

    @Autowired
    FactParametrFuncRepository factParametrFuncRepository;

    @Transactional(readOnly = true)
    public List<FactParametrFuncEntity> findAllByPropertyId(Long id) {
        return factParametrFuncRepository.findAllByDependencyTable_PropertyId(id);
    }

    @Transactional
    public void addAll(List<FactParametrFuncEntity> params) {
        factParametrFuncRepository.saveAll(params);
    }

}
