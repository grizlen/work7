package ru.griz.work7.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.griz.work7.db.entities.DocItemEntity;
import ru.griz.work7.db.entities.DocPurchaseEntity;
import ru.griz.work7.db.repositories.DocItemsRepository;
import ru.griz.work7.db.repositories.DocPurchaseRepository;
import ru.griz.work7.models.DocItem;
import ru.griz.work7.models.DocPurchase;
import ru.griz.work7.models.Document;
import ru.griz.work7.db.entities.DocEntity;
import ru.griz.work7.db.repositories.DocumentsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentsService {

    private final DocumentsRepository documentsRepository;
    private final DocPurchaseRepository docPurchaseRepository;
    private final DocItemsRepository docItemsRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Document> getAll() {
        List<DocEntity> docEntities = documentsRepository.findAll();
        List<Document> dtoList = docEntities.stream().map(entity -> modelMapper.map(entity, Document.class)).toList();
        return dtoList;
    }

    public DocPurchase SavePurchase(DocPurchase doc) {
        Long id = doc.getDocument().getId();
        if (id != null) {
            docItemsRepository.deleteAllByDocId(id);
        }
        DocEntity docEntity = new DocEntity();
        docEntity.setId(id);
        docEntity.setDate(doc.getDocument().getDate());
        docEntity.setType("PURCHASE");
        DocEntity savedDoc = documentsRepository.save(docEntity);
        id = docEntity.getId();
        DocPurchaseEntity purchaseEntity = new DocPurchaseEntity();
        purchaseEntity.setId(id);
        DocPurchaseEntity savedPurchase = docPurchaseRepository.save(purchaseEntity);
        Long finalId = id;
        List<DocItemEntity> itemEntities = doc.getItems().stream()
                .peek(item -> {
                    item.setId(null);
                    item.setDocId(finalId);
                })
                .map(item -> modelMapper.map(item, DocItemEntity.class))
                .toList();
        List<DocItem> docItems = docItemsRepository.saveAll(itemEntities).stream()
                .map(entity -> modelMapper.map(entity, DocItem.class))
                .toList();
        DocPurchase result = new DocPurchase();
        result.setDocument(modelMapper.map(savedDoc, Document.class));
        result.setItems(docItems);
        return result;
    }

    public DocPurchase getPurchase(Long id) {
        DocPurchaseEntity entity = docPurchaseRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        Optional<DocEntity> docEntity = documentsRepository.findById(entity.getId());
        if (docEntity == null) {
            return null;
        }
        DocPurchase result = new DocPurchase();
        result.setDocument(modelMapper.map(docEntity, Document.class));
        return result;
    }
}
