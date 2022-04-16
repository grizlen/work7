package ru.griz.work7.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.griz.work7.db.entities.DocItemEntity;

import java.util.List;

@Repository
public interface DocItemsRepository extends JpaRepository<DocItemEntity, Long> {
    void deleteAllByDocId(Long docId);

    List<DocItemEntity> findAllByDocId(Long id);
}
