package ru.griz.work7.view;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.griz.work7.controllers.DocumentsController;
import ru.griz.work7.models.DocPurchase;
import ru.griz.work7.models.Document;

public class JournalView extends ContentView {

    private final DocumentsController documentsController = DocumentsController.instance();

    protected ListView<Document> lvDocs = new ListView<>();

    public JournalView() {
        VBox.setVgrow(lvDocs, Priority.ALWAYS);
        getChildren().add(lvDocs);
        lvDocs.setOnMouseClicked(this::lvDocsMouseClicked);
    }

    @Override
    public void open() {
        lvDocs.getItems().clear();
        lvDocs.getItems().addAll(documentsController.getAll());
    }

    @Override
    public String getTitle() {
        return "Документы";
    }

    @Override
    public ViewCommand[] getCommands() {
        return new ViewCommand[] {
                ViewCommand.create("Новое Поступление", this::newDocument)
        };
    }

    private void lvDocsMouseClicked(MouseEvent mouseEvent) {
        Document selected = lvDocs.getSelectionModel().getSelectedItem();
        if (mouseEvent.getClickCount() == 2 && selected != null) {
            if (selected.getType().equals(Document.PURCHASE)) {
                DocPurchase purchase = documentsController.getPurchase(selected.getId());
                MainView.instance().setContent(DocPurchaseView.class).loadModel(purchase);
            }
        }
    }

    private void newDocument() {
        MainView.instance().setContent(DocPurchaseView.class);
    }
}
