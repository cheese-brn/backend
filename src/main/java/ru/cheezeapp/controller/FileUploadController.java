package ru.cheezeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cheezeapp.service.DocumentService;

@RestController
public class FileUploadController {

    @Autowired
    DocumentService documentService;

    /**
     * Метод обработки запроса на формирование и сохрениние документа штамма на компьютер клиента
     *
     * @param id id штамма
     * @return Ресурс с документом
     */
    @GetMapping("/strainToDocument/{id}")
    @ResponseBody
    public ResponseEntity<Resource> sendDocumentToClient(@PathVariable Long id) {
        Resource strainDocument = documentService.formStrainDocumentByIdAsResource(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + strainDocument.getFilename() + "\"").body(strainDocument);
    }

}
