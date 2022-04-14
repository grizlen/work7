package ru.griz.work7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.griz.work7.db.dtos.DocDTO;
import ru.griz.work7.services.DocumentsService;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DocumentsAPI {

    private static DocumentsAPI instance;

    public static DocumentsAPI instance() {
        return instance;
    }

    private final DocumentsService documentsService;

    @PostConstruct
    private void init() {
        System.out.println("DocumentsAPI");
        instance = this;
    }

    public List<DocDTO> getAll() {
        return documentsService.getAll();
    }

    public DocDTO postPurchase() {
        return documentsService.newPurchase();
    }
}
