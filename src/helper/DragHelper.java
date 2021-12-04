package helper;

import javafx.scene.Node;
import javafx.stage.Stage;

public class DragHelper {

    private static double x = 0.0, y = 0.0;
    private static Stage stage;

    public static void setDragable(Node node) {
        node.setOnMousePressed(e -> {
            x = e.getSceneX();
            y = e.getSceneY();
        });

        node.setOnMouseDragged(e -> {
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setX(e.getScreenX() - x);
            stage.setY(e.getScreenY() - y);
        });

    }

}
