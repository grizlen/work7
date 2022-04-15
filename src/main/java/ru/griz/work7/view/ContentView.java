package ru.griz.work7.view;

import javafx.scene.layout.VBox;

public abstract class ContentView extends VBox {

    public abstract void open();

    public abstract String getTitle();

    public abstract ViewCommand[] getCommands();
}
