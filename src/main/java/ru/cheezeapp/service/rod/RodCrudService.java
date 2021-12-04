package ru.cheezeapp.service.rod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cheezeapp.dao.RodStrainRepository;

@Service
public class RodCrudService {

    @Autowired
    RodStrainRepository rodStrainRepository;



}
