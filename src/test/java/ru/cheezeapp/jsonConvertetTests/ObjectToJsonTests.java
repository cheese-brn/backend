package ru.cheezeapp.jsonConvertetTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cheezeapp.entity.*;
import ru.cheezeapp.utils.jsonConverter.ObjectToJsonConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Тесты для конвертера программных объектов в JSON строку
 *
 * @author Pavel Chupikov
 */
public class ObjectToJsonTests {

    /**
     * ID тестовых сущностей
     */
    private static final Long TEST_ID = 1L;

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

    /**
     * Тестовая сущность рода
     */
    private static RodStrainEntity rod;

    /**
     * Тестовая сущность вида
     */
    private static VidStrainEntity vid;

    /**
     * Тестовая сущность свойства
     */
    private static PropertyEntity property;

    /**
     * Тестовая сущность подсвойства
     */
    private static SubPropertyEntity subproperty;

    /**
     * Тестовая сущность штамма
     */
    private static StrainEntity strain;

    /**
     * Инициализация всех сущностей
     */
    @BeforeAll
    static void initEntities() {
        rod = RodStrainEntity
                .builder()
                .id(TEST_ID)
                .name(TEST_NAME)
                .vids(new ArrayList<>())
                .build();
        vid = VidStrainEntity
                .builder()
                .id(TEST_ID)
                .name(TEST_NAME)
                .rodStrain(rod)
                .strains(new ArrayList<>())
                .build();
        property = PropertyEntity
                .builder()
                .id(TEST_ID)
                .name(TEST_NAME)
                .description(TEST_DESCRIPTION)
                .subProperties(new ArrayList<>())
                .build();
        subproperty = SubPropertyEntity.builder()
                .id(TEST_ID)
                .name(TEST_NAME)
                .property(property)
                .dataType(DataTypeEntity.builder().name(TEST_DATATYPE).build())
                .unit(TEST_UNIT)
                .build();
        strain = StrainEntity
                .builder()
                .id(TEST_ID)
                .exemplar(TEST_EXEMPLAR)
                .modification(TEST_MODIFICATION)
                .annotation(TEST_DESCRIPTION)
                .obtainingMethod(TEST_OBTAINING_METHOD)
                .origin(TEST_ORIGIN)
                .vidStrain(vid)
                .lost(false)
                .factParametrs(new ArrayList<>())
                .factParametrsFunc(new ArrayList<>())
                .build();
        rod.getVids().add(vid);
        vid.getStrains().add(strain);
        property.getSubProperties().add(subproperty);
    }

    @Test
    @DisplayName("Конвертирование сущности штамма в JSON строку")
    void strainToJsonTest() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy',' kk:mm");
        String result = "{" + System.lineSeparator() +
                "  \"id\" : " + TEST_ID + "," + System.lineSeparator() +
                "  \"rod\" : \"" + TEST_NAME + "\"," + System.lineSeparator() +
                "  \"rodId\" : " + TEST_ID + "," + System.lineSeparator() +
                "  \"vid\" : \"" + TEST_NAME + "\"," + System.lineSeparator() +
                "  \"vidId\" : " + TEST_ID + "," + System.lineSeparator() +
                "  \"author\" : \"Placeholder\"," + System.lineSeparator() +
                "  \"isLost\" : false," + System.lineSeparator() +
                "  \"date\" : \"" + formatForDateNow.format(dateNow) + "\"," + System.lineSeparator() +
                "  \"annotation\" : \"" + TEST_DESCRIPTION + "\"," + System.lineSeparator() +
                "  \"exemplar\" : \"" + TEST_EXEMPLAR + "\"," + System.lineSeparator() +
                "  \"modification\" : \"" + TEST_MODIFICATION + "\"," + System.lineSeparator() +
                "  \"obtainingMethod\" : \"" + TEST_OBTAINING_METHOD + "\"," + System.lineSeparator() +
                "  \"origin\" : \"" + TEST_ORIGIN + "\"," + System.lineSeparator() +
                "  \"factParams\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(result, ObjectToJsonConverter.strainToJson(strain));
    }

    @Test
    @DisplayName("Конвертирование сущности рода в JSON строку")
    void rodToJsonTest() {
        String result = "{" + System.lineSeparator() +
                "  \"id\" : " + TEST_ID + "," + System.lineSeparator() +
                "  \"name\" : \"" + TEST_NAME + "\"," + System.lineSeparator() +
                "  \"childrenCount\" : 1" + System.lineSeparator() +
                "}";
        assertEquals(result, ObjectToJsonConverter.rodToJson(rod));
    }

    @Test
    @DisplayName("Конвертирование сущности вида в JSON строку")
    void vidToJsonTest() {
        String result = "{" + System.lineSeparator() +
                "  \"id\" : " + TEST_ID + "," + System.lineSeparator() +
                "  \"name\" : \"" + TEST_NAME + "\"," + System.lineSeparator() +
                "  \"rodId\" : " + TEST_ID + "," + System.lineSeparator() +
                "  \"childrenCount\" : 1" + System.lineSeparator() +
                "}";
        assertEquals(result, ObjectToJsonConverter.vidToJson(vid));
    }

    @Test
    @DisplayName("Конвертирование сущности свойства в JSON строку")
    void propertyToJsonTest() {
        String result = "{" + System.lineSeparator() +
                "  \"id\" : " + TEST_ID + "," + System.lineSeparator() +
                "  \"name\" : \"" + TEST_NAME + "\"," + System.lineSeparator() +
                "  \"description\" : \"" + TEST_DESCRIPTION + "\"," + System.lineSeparator() +
                "  \"childrenCount\" : 1" + System.lineSeparator() +
                "}";
        assertEquals(result, ObjectToJsonConverter.propertyToJson(property));
    }

    @Test
    @DisplayName("Конвертирование сущности подсвойства в JSON строку")
    void subpropertyToJsonTest() {
        String result = "{" + System.lineSeparator() +
                "  \"id\" : " + TEST_ID + "," + System.lineSeparator() +
                "  \"name\" : \"" + TEST_NAME + "\"," + System.lineSeparator() +
                "  \"propertyName\" : \"" + TEST_NAME + "\"," + System.lineSeparator() +
                "  \"datatype\" : \"" + TEST_DATATYPE + "\"," + System.lineSeparator() +
                "  \"unit\" : \"" + TEST_UNIT + "\"" + System.lineSeparator() +
                "}";
        assertEquals(result, ObjectToJsonConverter.subpropertyToJson(subproperty));
    }

}
