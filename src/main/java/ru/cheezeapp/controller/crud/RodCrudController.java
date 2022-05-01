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

    /**
     * Метод обработки запроса на полное удаление рода. Вместе с родом удаляем все штаммы и виды данного рода
     *
     * @param id ID удаляемого рода
     * @return сообщение об обработке
     */
    @GetMapping("/rod/hard_delete/{id}")
    public String hardDeletionOfRodById(@PathVariable Long id) {
        log.info("[GET /rod/hard_delete/" + id + "]\tEntered hardDeletionOfRodById() method");
        try {
            rodCrudService.hardDeletionById(id);
            log.info("[GET /rod/hard_delete/" + id + "]\tHard deleted rod with id: " + id);
            return ResponseToJsonConverter.responseToJson("Род успешно удален");
        } catch (Exception e) {
            log.info("[GET /rod/hard_delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на полное удаление всех родов в корзине
     *
     * @return сообщение об обработке
     */
    @GetMapping("/rod/hard_delete_all")
    public String hardDeleteAllRods() {
        log.info("[GET /rod/hard_delete_all/]\tEntered hardDeleteAllRods() method");
        try {
            rodCrudService.hardDeleteAll();
            log.info("[GET /rod/hard_delete_all/]\thardDeleteAllRods() method done");
            return ResponseToJsonConverter.responseToJson("Рода удалены");
        } catch (Exception e) {
            log.info("[GET /rod/hard_delete_all/]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на восстановление рода
     *
     * @param id ID восстанавливаемого рода
     * @return сообщение об обработке
     */
    @GetMapping("/rod/restore/{id}")
    public String restoreRodById(@PathVariable Long id) {
        log.info("[GET /rod/restore/" + id + "]\tEntered RestoreRodById() method");
        try {
            rodCrudService.restoreById(id);
            log.info("[GET /rod/restore/" + id + "]\tRestored rod with id: " + id);
            return ResponseToJsonConverter.responseToJson("Род восстановлен из корзины");
        } catch (Exception e) {
            log.info("[GET /rod/delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

}
