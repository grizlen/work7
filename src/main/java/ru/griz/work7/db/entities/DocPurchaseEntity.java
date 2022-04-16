package ru.griz.work7.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doc_purchase")
@Getter
@Setter
@NoArgsConstructor
public class DocPurchaseEntity {
    @Id
    private Long id;
}
