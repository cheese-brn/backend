package ru.cheezeapp.controller.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.RodStrainEntity;
import ru.cheezeapp.service.rod.RodCrudService;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;
import ru.cheezeapp.utils.jsonConverter.ResponseToJsonConverter;

/**
 * Контроллер для обработки CRUD запросов, связанных с родами.
 *
 * @author Pavel Chupikov
 */
@RestController
@Slf4j
@CrossOrigin
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
                return ResponseToJsonConverter.responseToJson("Род был успешно добавлен");
            } else {
                rodCrudService.updateRod(rod);
                log.info("[POST /rod/send/]\tRod was updated, id = " + rod.getId());
                return ResponseToJsonConverter.responseToJson("Род был успешно обновлен");
            }
        } catch (Exception e) {
            log.info("[POST /rod/send/]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на мягкое удаление рода
     *
     * @param id ID удаляемого рода
     * @return сообщение об обработке
     */
    @GetMapping("/rod/delete/{id}")
    public String softDeletionOfRodById(@PathVariable Long id) {
        log.info("[GET /rod/delete/" + id + "]\tEntered softDeletionOfRodById() method");
        try {
            rodCrudService.softDeletionById(id);
            log.info("[GET /rod/delete/" + id + "]\tSoft deleted rod with id: " + id);
            return ResponseToJsonConverter.responseToJson("Род помещён в корзину");
        } catch (Exception e) {
            log.info("[GET /rod/delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

}
