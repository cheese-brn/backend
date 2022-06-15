package ru.cheezeapp.jsonConvertetTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.cheezeapp.dao.RodStrainRepository;
import ru.cheezeapp.dao.VidStrainRepository;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.utils.jsonConverter.JsonToObjectConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Тесты для конвертера JSON строки в программные объекты
 *
 * @author Pavel Chupikov
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JsonToObjectTests {

    /**
     * ID тестовых сущностей
     */
    private static Long testId;

    /**
     * Наименование тестовых сущностей
     */
    private static final String TEST_NAME = "Test Name";

    /**
     * Экземпляр тестового штамма
     */
    private static final String TEST_EXEMPLAR = "Test Exemplar";

    /**
     * Модификация тестового штамма
     */
    private static final String TEST_MODIFICATION = "Test Modification";

    /**
     * Способ получения тестового штамма
     */
    private static final String TEST_OBTAINING_METHOD = "Test Obtaining MEthod";

    /**
     * Источник тестового штамма
     */
    private static final String TEST_ORIGIN = "Test Origin";

    /**
     * Описание тестовых сущностей
     */
    private static final String TEST_DESCRIPTION = "Test Description";

    /**
     * Тип данных тестовых подсвойств
     */
    private static final String TEST_DATATYPE = "Test Datatype";

    /**
     * Единица измерения тестовых подсвойств
     */
    private static final String TEST_UNIT = "Test Unit";

    @Autowired
    JsonToObjectConverter jsonToObjectConverter;

    /**
     * Инициаизация тестовой базы данных
     *
     * @param rodStrainRepository репозиторий таблицы родов
     * @param vidStrainRepository репозиторий таблицы видов
     */
    @BeforeAll
    static void initTestDB(@Autowired RodStrainRepository rodStrainRepository,
                           @Autowired VidStrainRepository vidStrainRepository) {
        RodStrainEntity rod = RodStrainEntity
                .builder()
                .name(TEST_NAME)
                .vids(new ArrayList<>())
                .build();
        VidStrainEntity vid = VidStrainEntity
                .builder()
                .name(TEST_NAME)
                .rodStrain(rod)
                .strains(new ArrayList<>())
                .build();
        rod.getVids().add(vid);
        rodStrainRepository.save(rod);
        vidStrainRepository.save(vid);
        testId = rodStrainRepository.findAll().get(0).getId();
    }

    /**
     * Очистка тестовой базы данных
     *
     * @param rodStrainRepository репозиторий таблицы родов
     * @param vidStrainRepository репозиторий таблицы видов
     */
    @AfterAll
    static void clearTestDB(@Autowired RodStrainRepository rodStrainRepository,
                            @Autowired VidStrainRepository vidStrainRepository) {
        rodStrainRepository.deleteAll();
        vidStrainRepository.deleteAll();
    }

    @Test
    @DisplayName("Конвертирование JSON строки в сущность штамма")
    void jsonToStrainTest() {
        String strainJson = "{" + System.lineSeparator() +
                "  \"id\" : " + testId + "," + System.lineSeparator() +
                "  \"vidId\" : " + testId + "," + System.lineSeparator() +
                "  \"isLost\" : false," + System.lineSeparator() +
                "  \"annotation\" : \"" + TEST_DESCRIPTION + "\"," + System.lineSeparator() +
                "  \"exemplar\" : \"" + TEST_EXEMPLAR + "\"," + System.lineSeparator() +
                "  \"modification\" : \"" + TEST_MODIFICATION + "\"," + System.lineSeparator() +
                "  \"obtainingMethod\" : \"" + TEST_OBTAINING_METHOD + "\"," + System.lineSeparator() +
                "  \"origin\" : \"" + TEST_ORIGIN + "\"," + System.lineSeparator() +
                "  \"factParams\" : [ ]" + System.lineSeparator() +
                "}";
        StrainEntity strain = jsonToObjectConverter.jsonToStrain(strainJson);
        assertEquals(testId, strain.getId());
        assertEquals(TEST_EXEMPLAR, strain.getExemplar());
        assertEquals(TEST_MODIFICATION, strain.getModification());
        assertEquals(TEST_DESCRIPTION, strain.getAnnotation());
        assertEquals(TEST_OBTAINING_METHOD, strain.getObtainingMethod());
        assertEquals(TEST_ORIGIN, strain.getOrigin());
        assertEquals(testId, strain.getVidStrain().getId());
    }

    @Test
    @DisplayName("Конвертирование JSON строки в сущность рода")
    void jsonToRodTest() {
        String rodJson = "{" + System.lineSeparator() +
                "  \"rodId\" : " + testId + "," + System.lineSeparator() +
                "  \"name\" : \"" + TEST_NAME + "\"" + System.lineSeparator() +
                "}";
        RodStrainEntity rodStrain = jsonToObjectConverter.jsonToRod(rodJson);
        assertEquals(testId, rodStrain.getId());
        assertEquals(TEST_NAME, rodStrain.getName());
    }

    @Test
    @DisplayName("Конвертирование JSON строки в сущность вида")
    void jsonToVidTest() {
        String vidJson = "{" + System.lineSeparator() +
                "  \"vidId\" : " + testId + "," + System.lineSeparator() +
                "  \"name\" : \"" + TEST_NAME + "\"," + System.lineSeparator() +
                "  \"rodId\" : " + testId + System.lineSeparator() +
                "}";
        VidStrainEntity vidStrain = jsonToObjectConverter.jsonToVid(vidJson);
        assertEquals(testId, vidStrain.getId());
        assertEquals(TEST_NAME, vidStrain.getName());
        assertEquals(testId, vidStrain.getRodStrain().getId());
    }

    @Test
    @DisplayName("Конвертирование JSON строки в сущность свойства")
    void jsonToPropertyTest() {
        String propertyJson = "{" + System.lineSeparator() +
                "  \"id\" : " + testId + "," + System.lineSeparator() +
                "  \"name\" : \"" + TEST_NAME + "\"," + System.lineSeparator() +
                "  \"description\" : \"" + TEST_DESCRIPTION + "\"," + System.lineSeparator() +
                "  \"subProps\" : [ ]" + System.lineSeparator() +
                "}";
        PropertyEntity property = jsonToObjectConverter.jsonToProperty(propertyJson);
        assertEquals(testId, property.getId());
        assertEquals(TEST_NAME, property.getName());
        assertEquals(TEST_DESCRIPTION, property.getDescription());
        assertEquals(0, property.getSubProperties().size());
    }

}
