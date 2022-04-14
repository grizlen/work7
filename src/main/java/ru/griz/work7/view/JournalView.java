package ru.griz.work7.view;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.griz.work7.controllers.DocumentsAPI;
import ru.griz.work7.db.dtos.Document;

public class JournalView extends ContentView<Document> {

    private final DocumentsAPI documentsAPI = DocumentsAPI.instance();

    protected ListView<Document> lvDocs = new ListView<>();

    public JournalView() {
        VBox.setVgrow(lvDocs, Priority.ALWAYS);
        getChildren().add(lvDocs);
        lvDocs.setOnMouseClicked(this::lvDocsMouseClicked);
    }

    private void lvDocsMouseClicked(MouseEvent mouseEvent) {
        Document selected = lvDocs.getSelectionModel().getSelectedItem();
        if (mouseEvent.getClickCount() == 2 && selected != null) {
            MainView.instance().setContent(DocumentView.class).loadModel(selected);
        }
    }

    @Override
    public void open() {
        lvDocs.getItems().clear();
        lvDocs.getItems().addAll(documentsAPI.getAll());
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

    private void newDocument() {
        MainView.instance().setContent(DocumentView.class);
    }
}
