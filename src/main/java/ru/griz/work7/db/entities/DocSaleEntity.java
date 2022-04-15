package ru.griz.work7.db.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doc_sale")
public class DocSaleEntity {
    @Id
    private Long id;
}
