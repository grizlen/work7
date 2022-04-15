package ru.griz.work7.models;

import java.util.ArrayList;
import java.util.List;

public class DocPurchase{
    private final Document document;
    private final List<DocItem> items;

    public DocPurchase(){
        document = new Document();
        items = new ArrayList<>();
    }

    public void setDocument(Document doc) {
        if (doc.getType().equals(Document.PURCHASE) || doc.getType() == null)
        document.setId(doc.getId());
        document.setDate(doc.getDate());
        document.setType(Document.PURCHASE);
    }

    public Document getDocument() {
        return document;
    }

    public void setItems(List<DocItem> list) {
        items.clear();
        list.forEach(this::addItem);
    }

    public void addItem(DocItem item) {
        items.add(item);
    }

    public List<DocItem> getItems() {
        return items;
    }
}
