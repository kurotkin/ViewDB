package com.kurotkin.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Launcher extends Application {
    private static ClassPathXmlApplicationContext context;
    private Stage splashScreen;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The context is not initialized in the UI thread. Therefore, in the init () method, the UI is called via Platform.runLater()
     * @throws Exception
     */
    @Override
    public void init() throws InterruptedException {
        Platform.runLater(this::showSplash);
        Thread.sleep(5000);
        context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        Platform.runLater(this::closeSplash);
    }

    @Override
    public void start(Stage stage) throws IOException {
        SpringStageLoader.loadMain().show();
    }

    /**
     * Free the context
     */
    @Override
    public void stop() throws IOException {
        context.close();
    }

    /**
     * Download the screensaver in the usual way. We expose transparency everywhere
     */
    private void showSplash() {
        try {
            splashScreen = new Stage(StageStyle.TRANSPARENT);
            splashScreen.setTitle("Splash");
            Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/splash.fxml"));
            Scene scene = new Scene(root, Color.TRANSPARENT);
            splashScreen.setScene(scene);
            splashScreen.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the scene with a splash screen
     */
    private void closeSplash() {
        splashScreen.close();
    }
}
