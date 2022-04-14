package ru.griz.work7.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.griz.work7.db.dtos.DocDTO;
import ru.griz.work7.db.entities.DocEntity;
import ru.griz.work7.db.repositories.DocumentsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentsService {

    private final DocumentsRepository documentsRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<DocDTO> getAll() {
        List<DocEntity> docEntities = documentsRepository.findAll();
        List<DocDTO> dtoList = docEntities.stream().map(entity -> modelMapper.map(entity, DocDTO.class)).toList();
        return dtoList;
    }

    public DocDTO newPurchase() {
        DocEntity entity = new DocEntity();
        entity.setType("PURCHASE");
        DocEntity saved = documentsRepository.save(entity);
        DocDTO dto = modelMapper.map(saved, DocDTO.class);
        return dto;
    }
}
