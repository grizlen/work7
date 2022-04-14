package ru.griz.work7.db.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documents")
@Getter
@Setter
public class DocEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Date date;

    public DocEntity() {
        date = new Date();
    }
}
