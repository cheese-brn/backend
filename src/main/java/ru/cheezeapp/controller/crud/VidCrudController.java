package ru.cheezeapp.controller.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.entity.VidStrainEntity;
import ru.cheezeapp.service.vid.VidCrudService;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;
import ru.cheezeapp.utils.jsonConverter.ResponseToJsonConverter;

/**
 * Контроллер для обработки CRUD запросов, связанных со штаммами.
 *
 * @author Pavel Chupikov
 */
@RestController
@Slf4j
public class VidCrudController {

    @Autowired
    VidCrudService vidCrudService;

    @Autowired
    JsonToObjectConverter jsonToObjectConverter;

    /**
     * Метод обработки запроса на добавление или обновление вида.
     * Если ID вида == 0, значит добавяем, иначе, редактируем.
     *
     * @param vidJson JSON вида
     * @return сообщение об обработке
     */
    @PostMapping("/vid/send")
    public String sendVid(@RequestBody String vidJson) {
        log.info("[POST /vid/send/]\tEntered sendVid() method");
        try {
            VidStrainEntity vid = jsonToObjectConverter.jsonToVid(vidJson);
            if (vid.getId() == 0) {
                vidCrudService.addVid(vid);
                log.info("[POST /vid/send/]\tNew vid was created");
                return ResponseToJsonConverter.responseToJson("Вид был успешно добавлен");
            } else {
                vidCrudService.updateVid(vid);
                log.info("[POST /vid/send/]\tVid was updated, id = " + vid.getId());
                return ResponseToJsonConverter.responseToJson("Вид был успешно обновлен");
            }
        } catch (Exception e) {
            log.info("[POST /vid/send/]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на мягкое удаление вида
     *
     * @param id ID удаляемого вида
     * @return сообщение об обработке
     */
    @GetMapping("/vid/delete/{id}")
    public String softDeletionOfVidById(@PathVariable Long id) {
        log.info("[GET /vid/delete/" + id + "]\tEntered softDeletionOfVidById() method");
        try {
            vidCrudService.softDeletionById(id);
            log.info("[GET /vid/delete/" + id + "]\tSoft deleted vid with id: " + id);
            return ResponseToJsonConverter.responseToJson("Вид помещён в корзину");
        } catch (Exception e) {
            log.info("[GET /vid/delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

}
