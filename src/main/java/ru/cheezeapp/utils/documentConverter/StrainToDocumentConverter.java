package ru.cheezeapp.utils.documentConverter;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.cheezeapp.entity.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, содержащий методы формирования документа из объекта штамма
 */
public class StrainToDocumentConverter {

    /**
     * Заголовок документа
     */
    private static final String[] TITLE = {"ГНУ Сибирский научно-исследовательский институт сыроделия СО РАСХН",
            "КОЛЛЕКЦИЯ МИКРООРГАНИЗМОВ",
            "ПАСПОРТ ШТАММА"};

    /**
     * Заголовок таблицы
     */
    private static final String[] TABLE_HEADER = {"№", "Показатель", "Фактические данные"};

    /**
     * Шрифт документа
     */
    private static final String DOCUMENT_FONT = "Times New Roman";

    /**
     * Размер шрифта
     */
    private static final int FONT_SIZE = 12;

    /**
     * Ширина первой и второй колонок
     */
    private static final String FIRST_COLUMN_WIDTH = "3%";
    private static final String Second_COLUMN_WIDTH = "45%";

    private static final String RED_COLOR = "db3327";

    /**
     * Метод формирования документа из штамма и перевода его в объект Resources
     *
     * @param strain объект штамма
     * @return объект Resource для файла документа штамма
     * @throws MalformedURLException исключение при обработке URL
     */
    public static Resource strainToDocumentAsResource(StrainEntity strain) throws MalformedURLException {
        XWPFDocument document = strainToDocument(strain);
        String docName = DocumentUtils.DOCUMENTS_DIRECTORY + formStrainName(strain) + ".docx";
        DocumentUtils.saveDoc(document, docName);
        Path file = Paths.get(docName);
        return new UrlResource(file.toUri());
    }

    /**
     * Метод формирования Word документа из сущности штамма
     *
     * @param strain сущность штамма
     */
    public static XWPFDocument strainToDocument(StrainEntity strain) {
        XWPFDocument document = new XWPFDocument();
        createTitle(document, strain);
        createTableFromStrain(document, strain);
        return document;
    }

    /**
     * Процедура создания заголовка документа
     *
     * @param document объект документа
     */
    private static void createTitle(XWPFDocument document, StrainEntity strain) {
        XWPFParagraph title = document.createParagraph();
        setParagraph(title);
        for (String line : TITLE) {
            XWPFRun run = createRun(title, line);
            run.setBold(true);
            run.addBreak();
        }
        if (strain.isLost()) {
            XWPFRun run = createRun(title, "(Утрачен)");
            run.setBold(true);
            run.setColor(RED_COLOR);
            run.addBreak();
        }
    }

    /**
     * Процедура создания таблицы из фактических параметров штамма
     *
     * @param document документ штамма
     * @param strain   штамм
     */
    private static void createTableFromStrain(XWPFDocument document, StrainEntity strain) {
        XWPFTable table = document.createTable();
        table.setWidth("100%");
        createTableHeader(table);
        MultiValueMap<String, String> parameters = formParameters(strain);
        int paramCount = 1;
        for (String paramName : parameters.keySet()) {
            XWPFTableRow paramRow = table.createRow();
            setParagraphCell(paramRow, 0, 0, Integer.toString(paramCount));
            setParagraphCell(paramRow, 1, 0, paramName);
            XWPFTableCell factParamCell = paramRow.getCell(2);
            factParamCell.removeParagraph(0);
            int factParamCount = 0;
            for (String factParam : parameters.get(paramName)) {
                factParamCell.addParagraph();
                setParagraphCell(paramRow, 2, factParamCount, factParam);
                factParamCount++;
            }
            paramCount++;
        }
        XWPFTableRow annotationRow = table.createRow();
        DocumentUtils.mergeCellsHorizontal(annotationRow, 1, 2);
        setParagraphCell(annotationRow, 0, 0, Integer.toString(paramCount));
        setParagraphCell(annotationRow, 1, 0, "Другие сведения: " + strain.getAnnotation());
    }

    /**
     * Процедура формирования параграфа клетки таблицы. Берется первый параграф строки
     *
     * @param tableRow      строка таблицы
     * @param cellIndex     индекс клетки
     * @param paragraphText текст параграфа клетки
     */
    private static void setParagraphCell(XWPFTableRow tableRow, int cellIndex, int paragraphIndex, String paragraphText) {
        XWPFParagraph paragraph = tableRow.getCell(cellIndex).getParagraphArray(paragraphIndex);
        setParagraph(paragraph);
        createRun(paragraph, paragraphText);
    }

    /**
     * Процедура создания заголовка (первой строки) таблицы
     *
     * @param table таблица
     */
    private static void createTableHeader(XWPFTable table) {
        XWPFTableRow tableHeader = table.getRow(0);
        XWPFParagraph cellParagraph = tableHeader.getCell(0).getParagraphArray(0);
        setParagraph(cellParagraph);
        createRun(cellParagraph, TABLE_HEADER[0]);
        for (int i = 1; i < TABLE_HEADER.length; i++) {
            cellParagraph = tableHeader.addNewTableCell().getParagraphArray(0);
            setParagraph(cellParagraph);
            createRun(cellParagraph, TABLE_HEADER[i]);
        }
        tableHeader.getCell(0).setWidth(FIRST_COLUMN_WIDTH);
        tableHeader.getCell(1).setWidth(Second_COLUMN_WIDTH);
    }

    /**
     * Метод формирования словаря фактических параметров штамма.
     * Ключ - наименование свойства, значение - наименование подсвойства + фактическое значение
     *
     * @param strain сущность штамма
     * @return словарь фактических параметров
     */
    private static MultiValueMap<String, String> formParameters(StrainEntity strain) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("Наименование", formStrainName(strain));
        parameters.add("Происхождение", strain.getOrigin());
        parameters.add("Способ получения", strain.getObtainingMethod());
        List<PropertyEntity> strainProperties = strain.getFactParametrs().stream()
                .map(FactParametrEntity::getProperty)
                .distinct()
                .collect(Collectors.toList());
        for (FactParametrFuncEntity param : strain.getFactParametrsFunc())
            if (!strainProperties.contains(param.getDependencyTable().getProperty()))
                strainProperties.add(param.getDependencyTable().getProperty());
        for (PropertyEntity currentProperty : strainProperties) {
            StringBuilder value = new StringBuilder();
            List<FactParametrEntity> factParams = strain.getFactParametrs().stream()
                    .filter(param -> param.getProperty().equals(currentProperty))
                    .collect(Collectors.toList());
            for (FactParametrEntity factParametr : factParams) {
                if (factParametr.getSubProperty().getName() == null || factParametr.getSubProperty().getName().equals(""))
                    value.append(factParametr.getValue()).append(System.lineSeparator());
                else
                    value.append(factParametr.getSubProperty().getName()).append(": ").append(factParametr.getValue())
                            .append(System.lineSeparator());
            }
            List<DependencyTableEntity> functions = strain.getFactParametrsFunc().stream()
                    .map(FactParametrFuncEntity::getDependencyTable)
                    .filter(dependencyTable -> dependencyTable.getProperty().equals(currentProperty))
                    .distinct()
                    .collect(Collectors.toList());

            for (DependencyTableEntity function : functions) {
                if (function.getFunctionName() != null || !function.getFunctionName().equals(""))
                    value.append(function.getFunctionName()).append(": ");
                List<FactParametrFuncEntity> funcParams = function.getFactParametrsFunc().stream()
                        .filter(param -> param.getStrain().equals(strain))
                        .collect(Collectors.toList());
                String firstUnit = function.getFirstSubProperty().getUnit();
                String secondUnit = function.getSecondSubProperty().getUnit();
                for (FactParametrFuncEntity param : funcParams)
                    value.append(param.getFirstParametr()).append(" ").append(firstUnit).append(" (")
                            .append(param.getSecondParametr()).append(" ").append(secondUnit).append(") ");
            }
            parameters.add(currentProperty.getName(), value.toString());
        }
        return parameters;
    }

    /**
     * Процедура задания стиля параграфу.
     * Задаем выравнивание по центру и убираем интервал после
     *
     * @param paragraph параграф
     */
    private static void setParagraph(XWPFParagraph paragraph) {
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        paragraph.setSpacingAfter(0);
    }

    /**
     * Метод создания объекта XWPFRun для параграфа
     *
     * @param paragraph параграф
     * @param text      текст XWPFRun
     * @return объект XWPFRun
     */
    private static XWPFRun createRun(XWPFParagraph paragraph, String text) {
        XWPFRun run = paragraph.createRun();
        run.setFontFamily(DOCUMENT_FONT);
        run.setFontSize(FONT_SIZE);
        run.setText(text);
        return run;
    }

    /**
     * Метод формирования имени штамма из рода, вида, экземпляра и модификации штамма
     *
     * @param strain штамм для формирования имени документа
     * @return имя документа
     */
    private static String formStrainName(StrainEntity strain) {
        return strain.getVidStrain().getRodStrain().getName() + " " +
                strain.getVidStrain().getName() + " " +
                strain.getExemplar() + " " +
                strain.getModification();
    }

}
