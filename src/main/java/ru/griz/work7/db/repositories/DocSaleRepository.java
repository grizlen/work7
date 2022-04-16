package ru.griz.work7.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.griz.work7.db.entities.DocSaleEntity;

@Repository
public interface DocSaleRepository extends JpaRepository<DocSaleEntity, Long> {
}
