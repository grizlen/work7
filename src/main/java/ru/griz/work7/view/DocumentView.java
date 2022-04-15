package ru.griz.work7.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.griz.work7.models.DocItem;

public abstract class DocumentView<T> extends ContentView {

    protected T model;

    private final HBox headerBox = new HBox();
    protected final Label lblId = new Label();
    protected final Label lblDate = new Label();
    protected final ListView<DocItem> lvItems = new ListView<>();
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

    protected void newItem() {
        System.out.println("newItem");
    }

    protected abstract void loadModel(T model);

    @Override
    public void open() {
        loadModel(null);
    }

    @Override
    public ViewCommand[] getCommands() {
        return new ViewCommand[] {
                ViewCommand.create("Сохранить", this::save),
                ViewCommand.create("Документы", this::journal)
        };
    }

    protected void save() {
        System.out.println("save");
        journal();
    }

    protected void journal() {
        MainView.instance().setContent(JournalView.class);
    }
}
