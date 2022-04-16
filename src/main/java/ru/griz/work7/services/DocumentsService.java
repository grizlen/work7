package ru.griz.work7.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Transactional
    public DocPurchase SavePurchase(DocPurchase doc) {
        clearDocItems(doc);
        saveDocumentEntity(doc);
        savePurchaseEntity(doc);
        saveDocItems(doc);
        return doc;
    }

    private void clearDocItems(DocPurchase doc) {
        log.info("Clear items for purchase id {}", doc.getDocument().getId());
        if (doc.getDocument().getId() != null) {
            docItemsRepository.deleteAllByDocId(doc.getDocument().getId());
        }
    }

    private void saveDocumentEntity(DocPurchase doc) {
        log.info("save document entity for purchase id {}", doc.getDocument().getId());
        DocEntity docEntity = new DocEntity();
        docEntity.setId(doc.getDocument().getId());
        docEntity.setDate(doc.getDocument().getDate());
        docEntity.setType("PURCHASE");
        doc.getDocument().setId(documentsRepository.save(docEntity).getId());
    }

    private void savePurchaseEntity(DocPurchase doc) {
        log.info("save entity for purchase id {}", doc.getDocument().getId());
        DocPurchaseEntity purchaseEntity = new DocPurchaseEntity();
        purchaseEntity.setId(doc.getDocument().getId());
        DocPurchaseEntity savedPurchase = docPurchaseRepository.save(purchaseEntity);
    }

    private void saveDocItems(DocPurchase doc) {
        log.info("save document items for purchase id {}", doc.getDocument().getId());
        List<DocItemEntity> entities = doc.getItems().stream()
                .map(item -> {
                    DocItemEntity entity = new DocItemEntity();
                    entity.setId(null);
                    entity.setDocId(doc.getDocument().getId());
                    return entity;
                })
                .toList();
        List<DocItemEntity> savedEntities = docItemsRepository.saveAll(entities);
        List<DocItem> items = savedEntities.stream()
                .map(entity -> {
                    DocItem item = new DocItem();
                    item.setId(entity.getId());
                    return item;
                })
                .toList();
        doc.setItems(items);
    }

    public DocPurchase getPurchase(Long id) {
        DocEntity documentEntity = getDocumentEntity(id);
        if (documentEntity == null) {
            return null;
        }
        DocPurchaseEntity purchaseEntity = getPurchaseEntity(id);
        if (purchaseEntity == null) {
            return null;
        }
        List<DocItemEntity> itemEntities = getDocItemEntities(id);
        List<DocItem> items = itemEntities.stream()
                .map(entity -> {
                    DocItem item = new DocItem();
                    item.setId(entity.getId());
                    return item;
                })
                .toList();
        DocPurchase result = new DocPurchase();
        result.getDocument().setId(documentEntity.getId());
        result.getDocument().setDate(documentEntity.getDate());
        result.getDocument().setType(documentEntity.getType());
        result.setItems(items);
        return result;
    }

    private DocEntity getDocumentEntity(Long id) {
        log.info("get document entity for id {}" ,id);
        return documentsRepository.findById(id).orElse(null);
    }

    private DocPurchaseEntity getPurchaseEntity(Long id) {
        log.info("get purchase entity for id {}" ,id);
        return docPurchaseRepository.findById(id).orElse(null);
    }

    private List<DocItemEntity> getDocItemEntities(Long id) {
        log.info("get document items for id {}" ,id);
        return docItemsRepository.findAllByDocId(id);
    }
}
