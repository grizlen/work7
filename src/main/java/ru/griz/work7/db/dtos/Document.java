package ru.griz.work7.db.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Document {
    private Long id;
    private String type;
    private Date date;

    public Document() {
        date = new Date();
    }

    @Override
    public String toString() {
        return "DocDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                '}';
    }
}
