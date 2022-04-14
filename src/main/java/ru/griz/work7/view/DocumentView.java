package ru.griz.work7.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.griz.work7.db.dtos.DocDTO;
import ru.griz.work7.db.dtos.DocItem;

public class DocumentView extends ContentView<DocDTO> {

    private DocDTO model;

    private final HBox headerBox = new HBox();
    private final Label lblId = new Label();
    private final Label lblDate = new Label();
    private final ListView<DocItem> lvItems = new ListView<>();
    private final HBox footerBox = new HBox();

    public DocumentView() {
        setPadding(new Insets(4));
        setSpacing(4);
        headerBox.setSpacing(4);
        headerBox.getChildren().addAll(lblId, lblDate);
        VBox.setVgrow(lvItems, Priority.ALWAYS);
        footerBox.setAlignment(Pos.CENTER_RIGHT);
        footerBox.getChildren().add(ViewCommand.create("+", this::newItem).asButton());
        getChildren().addAll(headerBox, lvItems, footerBox);
    }

    private void newItem() {
        System.out.println("newItem");
    }

    protected void loadModel(DocDTO model) {
        this.model = model;
        lblId.setText("№ " + model.getId());
        lblDate.setText("от " + model.getDate());
    }

    @Override
    public void open() {
        loadModel(new DocDTO());
    }

    @Override
    public String getTitle() {
        return "Документ";
    }

    @Override
    public ViewCommand[] getCommands() {
        return new ViewCommand[] {
                ViewCommand.create("Сохранить", this::save),
                ViewCommand.create("Документы", this::journal)
        };
    }

    private void save() {
        System.out.println("save");
        journal();
    }

    private void journal() {
        MainView.instance().setContent(JournalView.class);
    }
}
