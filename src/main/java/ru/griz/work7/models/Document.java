package ru.griz.work7.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Document {
    public static final String PURCHASE = "PURCHASE";
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
