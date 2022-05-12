package ru.cheezeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.cheezeapp.dao.StrainRepository;
import ru.cheezeapp.entity.StrainEntity;
import ru.cheezeapp.utils.documentConverter.StrainToDocumentConverter;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Optional;

/**
 * Сервисы для работы с документами
 *
 * @author Pavel Chupikov
 */
@Service
public class DocumentService {

    @Autowired
    StrainRepository strainRepository;

    @Autowired
    JsonToObjectConverter jsonToObjectConverter;

    /**
     * Метод, возвращающий файл документа штамма как объект Resource
     *
     * @param id id штамма
     * @return объект Resource с файлом документа
     */
    public Resource formStrainDocumentByIdAsResource(Long id) {
        Optional<StrainEntity> strain = strainRepository.findById(id);
        if (!strain.isPresent()) {
            return null;
        }
        try {
            Resource resource = StrainToDocumentConverter.strainToDocumentAsResource(strain.get());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException(
                        "Could not read file: " + resource.getFilename());
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
