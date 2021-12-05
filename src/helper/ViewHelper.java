package helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.io.IOException;

public class ViewHelper {

    double screenX, screenY;

    public void loadFxml(BorderPane borderPane, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        borderPane.setCenter(root);
    }

    public void loadPopupFiles(PopOver popOver, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        popOver.setContentNode(root);
    }

    public void loadCal() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(PathHelper.CALCULATOR_FILE));
        Stage stage = new Stage();
        stage.setTitle("Calculator");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 400, 500));
        stage.show();
    }

}
