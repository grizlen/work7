package ru.griz.work7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.griz.work7.models.DocPurchase;
import ru.griz.work7.models.Document;
import ru.griz.work7.services.DocumentsService;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DocumentsController {

    private static DocumentsController instance;

    public static DocumentsController instance() {
        return instance;
    }

    private final DocumentsService documentsService;

    @PostConstruct
    private void init() {
        System.out.println("DocumentsAPI");
        instance = this;
    }

    public List<Document> getAll() {
        return documentsService.getAll();
    }

    public DocPurchase getPurchase(Long id) {
        return documentsService.getPurchase(id);
    }

    public DocPurchase SavePurchase(DocPurchase doc) {
        return documentsService.SavePurchase(doc);
    }
}
