package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main extends Application {
    static Database database;
    static DatabaseResponse<?> previousResponse;
    static Path logFile = Path.of(System.getProperty("user.home"))
            .resolve("Desktop")
            .resolve("logfile.txt");
    
    public static void main(String[] args) {
        System.out.println("Hello World");
        
        if (Files.notExists(logFile)) {
            try {
                Files.createDirectories(logFile.getParent());
                Files.createFile(logFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Unable to create log file.");
            }
        }
        
        // TODO replace
        database = new TestDatabase();
        launch();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("ui.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    
    public static Database getDatabase() {
        return database;
    }
    
    public static void setDatabase(Database database) {
        Main.database = database;
    }
}
