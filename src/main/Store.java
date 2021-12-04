package main;

import database.DBConnection;
import helper.PathHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Store extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(PathHelper.MAIN_FILE));
        primaryStage.setTitle("Store App");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        DBConnection.dbConnect();
    }

    @Override
    public void stop() throws Exception {
        DBConnection.disConnect();
    }

}
