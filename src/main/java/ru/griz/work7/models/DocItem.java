package ru.griz.work7.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocItem {
    private Long id;

    @Override
    public String toString() {
        return "DocItem{" +
                "id=" + id +
                '}';
    }
}
