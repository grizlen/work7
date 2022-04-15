package ru.griz.work7.view;

import ru.griz.work7.controllers.DocumentsController;
import ru.griz.work7.models.DocItem;
import ru.griz.work7.models.DocPurchase;

public class DocPurchaseView extends DocumentView<DocPurchase> {

    private final DocumentsController documentsController = DocumentsController.instance();

    @Override
    public String getTitle() {
        return "Поступление";
    }

    @Override
    protected void loadModel(DocPurchase model) {
        this.model = (model == null) ? new DocPurchase() : model;
        lblId.setText("№ " + this.model.getDocument().getId());
        lblDate.setText("от " + this.model.getDocument().getDate());
        lvItems.getItems().clear();
        lvItems.getItems().addAll(this.model.getItems());
    }

    @Override
    protected void newItem() {
        super.newItem();
        DocItem docItem = new DocItem();
        model.addItem(docItem);
        lvItems.getItems().add(docItem);
    }

    @Override
    protected void save() {
        documentsController.SavePurchase(model);
        journal();
    }
}
