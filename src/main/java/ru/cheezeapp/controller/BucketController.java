package ru.cheezeapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.cheezeapp.service.property.PropertyCrudService;
import ru.cheezeapp.service.rod.RodCrudService;
import ru.cheezeapp.service.strain.StrainCrudService;
import ru.cheezeapp.service.vid.VidCrudService;
import ru.cheezeapp.utils.jsonConverter.ResponseToJsonConverter;

/**
 * Контроллер запросов над операцией с корзиной
 *
 * @author Pavel Chupikov
 */
@RestController
@Slf4j
@CrossOrigin
public class BucketController {

    @Autowired
    StrainCrudService strainCrudService;

    @Autowired
    PropertyCrudService propertyCrudService;

    @Autowired
    VidCrudService vidCrudService;

    @Autowired
    RodCrudService rodCrudService;

    /**
     * Обработка запроса на очистку корзины
     *
     * @return сообщение о выполении запроса
     */
    @GetMapping("/bucket/clear")
    public String clearBucket() {
        log.info("[GET /bucket/clear]\tEntered clearBucket() method");
        try {
            strainCrudService.hardDeleteAll();
            propertyCrudService.hardDeleteAll();
            vidCrudService.hardDeleteAll();
            rodCrudService.hardDeleteAll();
            log.info("[GET /bucket/clear]\tCleared bucket");
            return ResponseToJsonConverter.responseToJson("Корзина очищена");
        }
        catch (Exception e) {
            log.info("[GET /bucket/clear]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на полное удаление свойства
     *
     * @param id ID удаляемого свойства
     * @return сообщение об обработке
     */
    @GetMapping("/property/hard_delete/{id}")
    public String hardDeletionOfPropertyById(@PathVariable Long id) {
        log.info("[GET /property/hard_delete/" + id + "]\tEntered hardDeletionOfPropertyById() method");
        try {
            propertyCrudService.hardDeletionById(id);
            log.info("[GET /property/hard_delete/" + id + "]\tHard deleted property with id: " + id);
            return ResponseToJsonConverter.responseToJson("Свойство удалено");
        } catch (Exception e) {
            log.info("[GET /property/hard_delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на полное удаление свойства
     *
     * @param id ID удаляемого свойства
     * @return сообщение об обработке
     */
    @GetMapping("/property/restore/{id}")
    public String restoreOfPropertyById(@PathVariable Long id) {
        log.info("[GET /property/restore/" + id + "]\tEntered restoreOfPropertyById() method");
        try {
            propertyCrudService.restoreById(id);
            log.info("[GET /property/restore/" + id + "]\tRestored property with id: " + id);
            return ResponseToJsonConverter.responseToJson("Свойство восстановлено из корзины");
        } catch (Exception e) {
            log.info("[GET /property/restore/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод удаления всех свойств из корзины
     *
     * @return сообщение об обработке
     */
    @GetMapping("/property/hard_delete_all")
    public String hardDeleteAllProperties() {
        log.info("[GET /property/hard_delete_all/]\tEntered hardDeleteAllProperties() method");
        try {
            propertyCrudService.hardDeleteAll();
            log.info("[GET /property/hard_delete_all/]\tAll deleted properties were hardly deleted");
            return ResponseToJsonConverter.responseToJson("Корзина очищена от свойств");
        } catch (Exception e) {
            log.info("[GET /property/hard_delete_all/]\tThrows exception: " + e.getMessage());
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

    /**
     * Метод обработки запроса на полное удаление штамма из БД
     *
     * @param id ID удаляемого штамма
     * @return сообщение об обработке
     */
    @GetMapping("/strain/hard_delete/{id}")
    public String hardDeletionOfStrainById(@PathVariable Long id) {
        log.info("[GET /strain/hard_delete/" + id + "]\tEntered hardDeletionOfStrainById() method");
        try {
            strainCrudService.hardDeletionOfStrainById(id);
            log.info("[GET /strain/hard_delete/" + id + "]\tHard deleted strain with id: " + id);
            return ResponseToJsonConverter.responseToJson("Штамм успешно удалён");
        } catch (Exception e) {
            log.info("[GET /strain/hard_delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки апроса на удаление всех штаммов из корзины
     *
     * @return сообщение об обработке
     */
    @GetMapping("/strain/hard_delete_all")
    public String hardDeleteAllStrains() {
        log.info("[GET /strain/hard_delete_all/]\tEntered hardDeleteAllStrains() method");
        try {
            strainCrudService.hardDeleteAll();
            log.info("[GET /strain/hard_delete_all/]\tAll deleted strains were hardly deleted");
            return ResponseToJsonConverter.responseToJson("Корзина очищена от штаммов");
        } catch (Exception e) {
            log.info("[GET /strain/hard_delete_all/]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на восстановление штамма из корзины
     *
     * @param id ID восстанавливаемого штамма
     * @return сообщение об обработке
     */
    @GetMapping("/strain/restore/{id}")
    public String restoreStrain(@PathVariable Long id) {
        log.info("[GET /strain/restore/" + id + "]\tEntered restoreStrain() method");
        try {
            strainCrudService.restoreStrain(id);
            log.info("[GET /strain/restore/" + id + "]\tStrain was restored from bucket");
            return ResponseToJsonConverter.responseToJson("Штамм восстновлен из корзины");
        } catch (Exception e) {
            log.info("[GET /strain/restore/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на полное удаление вида из БД. Вместе с видом удаляются все штаммы
     *
     * @param id ID удаляемого вида
     * @return сообщение об обработке
     */
    @GetMapping("/vid/hard_delete/{id}")
    public String hardDeletionOfVidById(@PathVariable Long id) {
        log.info("[GET /vid/hard_delete/" + id + "]\tEntered hardDeletionOfVidById() method");
        try {
            vidCrudService.hardDeletionById(id);
            log.info("[GET /vid/hard_delete/" + id + "]\tHard deleted vid with id: " + id);
            return ResponseToJsonConverter.responseToJson("Вид успешно удален");
        } catch (Exception e) {
            log.info("[GET /vid/hard_delete/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на полное удаление всех удаленных видов из БД. Вместе с видом удаляются все штаммы
     *
     * @return сообщение об обработке
     */
    @GetMapping("/vid/hard_delete_all/")
    public String hardDeleteAllVids() {
        log.info("[GET /vid/hard_delete_all/]\tEntered hardDeleteAllVids() method");
        try {
            vidCrudService.hardDeleteAll();
            log.info("[GET /vid/hard_delete_all/]\tHard deleted all vids");
            return ResponseToJsonConverter.responseToJson("Вид помещён в корзину");
        } catch (Exception e) {
            log.info("[GET /vid/hard_delete/]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

    /**
     * Метод обработки запроса на восстановление вида из корзины.
     *
     * @param id ID восстанавливаемого вида
     * @return сообщение об обработке
     */
    @GetMapping("/vid/restore/{id}")
    public String restoreVidById(@PathVariable Long id) {
        log.info("[GET /vid/restore/" + id + "]\tEntered restoreVidById() method");
        try {
            vidCrudService.restoreById(id);
            log.info("[GET /vid/restore/" + id + "]\tRestored vid with id: " + id);
            return ResponseToJsonConverter.responseToJson("Вид успешно восстановлен");
        } catch (Exception e) {
            log.info("[GET /vid/restore/" + id + "]\tThrows exception: " + e.getMessage());
            return ResponseToJsonConverter.responseToJson(e.getMessage());
        }
    }

}
