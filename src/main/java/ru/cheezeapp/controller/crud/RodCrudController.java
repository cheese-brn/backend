package ru.cheezeapp.controller.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.service.rod.RodCrudService;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;

/**
 * Контроллер для обработки CRUD запросов, связанных с родами.
 */
@RestController
@Slf4j
public class RodCrudController {

    @Autowired
    RodCrudService rodCrudService;

    @Autowired
    JsonToObjectConverter jsonToObjectConverter;

    /**
     * Метод обработки запроса на добавление или обновление рода.
     * Если ID рода == 0, значит добавяем, иначе, редактируем.
     *
     * @param rodJson JSON рода
     * @return сообщение об обработке
     */
    @PostMapping("/rod/send")
    public String sendRod(@RequestBody String rodJson) {
        log.info("[POST /rod/send/]\tEntered sendRod() method");
        try {
            RodStrainEntity rod = jsonToObjectConverter.jsonToRod(rodJson);
            if (rod.getId() == 0) {
                rodCrudService.addRod(rod);
                log.info("[POST /rod/send/]\tNew rod was created");
                return "Род был успешно добавлен";
            } else {
                rodCrudService.updateRod(rod);
                log.info("[POST /rod/send/]\tRod was updated, id = " + rod.getId());
                return "Род был успешно обновлен";
            }
        } catch (Exception e) {
            log.info("[POST /rod/send/]\tThrows exception: " + e.getMessage());
            return e.getMessage();
        }
    }

}
