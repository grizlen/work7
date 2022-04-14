package ru.griz.work7.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainView extends BorderPane {

    private static MainView instance;

    public static MainView instance() {
        return instance != null ? instance : (instance = new MainView());
    }

    private final NavBox navBox = new NavBox();

    private MainView() {
        System.out.println("MainView");
        setTop(navBox);
    }

    public <T extends ContentView> T setContent(Class<T> viewClass) {
        try {
            T result = viewClass.getDeclaredConstructor().newInstance();
            setCenter(result);
            navBox.setTitle(result.getTitle());
            navBox.setCommands(result.getCommands());
            result.open();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class NavBox extends HBox {
        private final Label lblTitle = new Label();
        private final HBox commansBox = new HBox();

        private NavBox (){
            BorderStroke borderStroke = new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(1));
            Border border = new Border(borderStroke);
            setBorder(border);
            setPadding(new Insets(4));
            setSpacing(4);
            commansBox.setAlignment(Pos.CENTER_RIGHT);
            commansBox.setSpacing(4);
            HBox.setHgrow(commansBox, Priority.ALWAYS);
            getChildren().addAll(lblTitle, commansBox);
        }

        public void setTitle(String title) {
            lblTitle.setText(title);
        }

        public void setCommands(ViewCommand... commands) {
            commansBox.getChildren().clear();
            for (ViewCommand cmd : commands) {
                commansBox.getChildren().add(cmd.asButton());
            }
        }
    }
}
