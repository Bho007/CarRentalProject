import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jdbc.Query;
import model.TimePeriod;
import model.Vehicle;
import model.VehicleStatus;
import model.VehicleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jdbc.Query.*;

//import static jdbc.FirstExample.x;

public class Main extends Application {

    public static void main(String[] args) {
        System.out.println("Hello World");
        //launch();
        // Setup the connection
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(getAllAvailableVehicles(null, "", null));


        // Free up connection resources
        if (conn != null) {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert (conn.isClosed() && stmt.isClosed());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // TODO: Figure out how to remove this
        System.exit(0);

    }

    @Override
    public void start(Stage stage) throws Exception {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }


}
