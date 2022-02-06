package ru.cheezeapp.controller.crud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.entity.VidStrainEntity;
import ru.cheezeapp.service.vid.VidCrudService;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;

/**
 * Контроллер для обработки CRUD запросов, связанных со штаммами.
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
                return "Вид был успешно добавлен";
            } else {
                vidCrudService.updateVid(vid);
                log.info("[POST /vid/send/]\tVid was updated, id = " + vid.getId());
                return "Вид был успешно обновлен";
            }
        } catch (Exception e) {
            log.info("[POST /vid/send/]\tThrows exception: " + e.getMessage());
            return e.getMessage();
        }
    }

}
