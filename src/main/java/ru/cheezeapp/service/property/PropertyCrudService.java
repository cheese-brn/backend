package ru.cheezeapp.service.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cheezeapp.dao.PropertyRepository;

@Service
@Slf4j
public class PropertyCrudService {

    @Autowired
    PropertyRepository propertyRepository;

}
