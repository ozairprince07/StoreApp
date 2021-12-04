package customWidgets;

import helper.DragHelper;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class CustomDialog extends Stage {

//    Interpolator EXP_IN = new Interpolator() {
//        @Override
//        protected double curve(double t) {
//            return (t == 1.0) ? 1.0 : 1 - Math.pow(2.0, -10 * t);
//        }
//    };
//
//    Interpolator EXP_OUT = new Interpolator() {
//        @Override
//        protected double curve(double t) {
//            return (t == 0.0) ? 0.0 : 1 - Math.pow(2.0, 10 * (t - 1));
//        }
//    };

    private final ScaleTransition scale1 = new ScaleTransition();
    private final ScaleTransition scale2 = new ScaleTransition();
    private final SequentialTransition animation = new SequentialTransition(scale1, scale2);

    public CustomDialog(String file) throws IOException {

        Pane pane = new Pane();

        // *** === Animate the Dialog

        scale1.setFromX(0.01);
        scale1.setFromY(0.01);
        scale1.setToY(1.0);
        scale1.setDuration(Duration.seconds(0.33));
//        scale1.setInterpolator(EXP_IN);
        scale1.setNode(pane);

        scale2.setFromX(0.01);
        scale2.setToX(1.0);
        scale2.setDuration(Duration.seconds(0.33));
//        scale2.setInterpolator(EXP_OUT);
        scale2.setNode(pane);

        Parent root = FXMLLoader.load(getClass().getResource(file));
        initStyle(StageStyle.TRANSPARENT);
        initModality(Modality.APPLICATION_MODAL);

        Button closeBtn = new Button("Close");

        VBox box = new VBox(closeBtn, new Separator(Orientation.HORIZONTAL), root);

        pane.getChildren().addAll(box);

        Scene scene = new Scene(pane, null);
        setScene(scene);

        DragHelper.setDragable(pane);

        closeBtn.setOnAction(e -> closeDialog());

    }

    public void showDialog() throws IOException {
        show();
        animation.play();
    }

    public void closeDialog() {
        animation.setOnFinished(e -> close());
        animation.setAutoReverse(true);
        animation.setCycleCount(2);
        animation.playFrom(Duration.seconds(0.66));
    }

}
