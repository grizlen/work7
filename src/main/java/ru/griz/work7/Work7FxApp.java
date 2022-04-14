package ru.griz.work7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.griz.work7.view.JournalView;
import ru.griz.work7.view.MainView;

@SpringBootApplication
public class Work7FxApp extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = SpringApplication.run(getClass());
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(MainView.instance(), 800, 600));
        MainView.instance().setContent(JournalView.class);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }
}
