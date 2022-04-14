package ru.griz.work7.view;

import javafx.scene.control.Button;

public class ViewCommand {

    public static ViewCommand create(String title, CommandAction action) {
        return new ViewCommand(title, action);
    }

    private final String title;
    private final CommandAction action;

    public ViewCommand(String title, CommandAction action) {
        this.title = title;
        this.action = action;
    }

    public void execute() {
        System.out.println("command " + title);
        if (action != null) {
            action.execute();
        }
    }

    public Button asButton() {
        Button button = new Button();
        button.setText(title);
        button.setOnAction(actionEvent -> execute());
        return button;
    }

    public interface CommandAction {
        void execute();
    }
}
