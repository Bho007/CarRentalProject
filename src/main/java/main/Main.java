package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Database database;
    static DatabaseResponse<?> previousResponse;
    
    public static void main(String[] args) {
        System.out.println("Hello World");
        launch();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("ui.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void setDatabase(Database database) {
        Main.database = database;
    }
}
