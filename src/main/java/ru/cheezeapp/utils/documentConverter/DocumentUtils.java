package ru.cheezeapp.utils.documentConverter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class DocumentUtils {

    /**
     * Директория документов
     */
    public static final String DOCUMENTS_DIRECTORY = "src/documents/";

    /**
     * Процедура удаления документа
     */
    public static void deleteAllDocs() {
        for(File document : Objects.requireNonNull(Paths.get(DOCUMENTS_DIRECTORY).toFile().listFiles()))
            document.deleteOnExit();
    }

    /**
     * Процедура сохранения документа
     *
     * @param document документ для сохранения
     * @param docName  имя документа
     */
    public static void saveDoc(XWPFDocument document, String docName) {
        try {
            FileOutputStream out = new FileOutputStream(docName);
            document.write(out);
            out.close();
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Процедура слияния ячеек строки
     *
     * @param row      строка
     * @param startCol индекс ячейки начала слияния
     * @param endCol   индекс ячейки конца слияния
     */
    public static void mergeCellsHorizontal(XWPFTableRow row, int startCol, int endCol) {
        for (int cellIndex = startCol; cellIndex <= endCol; cellIndex++) {
            XWPFTableCell cell = row.getCell(cellIndex);
            if (cellIndex == startCol) {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

}
