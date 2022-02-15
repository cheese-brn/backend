package ru.cheezeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.cheezeapp.utils.documentConverter.DocumentUtils;

@SpringBootApplication
public class Application {

    /**
     * Метод запуска приложения.
     * Перед запуском приложения удаляем все сформированные отчеты по штаммам из папки documents.
     * Если папки documents нет, создаем ее.
     *
     * @param args агрументы
     */
    public static void main(String[] args) {
        DocumentUtils.deleteAllDocs();
        DocumentUtils.createDocumentFolder();
        SpringApplication.run(Application.class, args);
    }

}
