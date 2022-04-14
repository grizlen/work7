package ru.griz.work7.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.griz.work7.db.dtos.Document;
import ru.griz.work7.db.entities.DocEntity;
import ru.griz.work7.db.repositories.DocumentsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentsService {

    private final DocumentsRepository documentsRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Document> getAll() {
        List<DocEntity> docEntities = documentsRepository.findAll();
        List<Document> dtoList = docEntities.stream().map(entity -> modelMapper.map(entity, Document.class)).toList();
        return dtoList;
    }

    public Document newPurchase() {
        DocEntity entity = new DocEntity();
        entity.setType("PURCHASE");
        DocEntity saved = documentsRepository.save(entity);
        Document dto = modelMapper.map(saved, Document.class);
        return dto;
    }
}
