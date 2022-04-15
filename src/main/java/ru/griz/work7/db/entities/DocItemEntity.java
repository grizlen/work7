package ru.griz.work7.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "doc_items")
public class DocItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long docId;
}
