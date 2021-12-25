package customWidgets;

import helper.PathHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Company;

public class CompanyListCell extends ListCell<Company> {

    private final HBox hBox = new HBox();
    private final VBox infoBox = new VBox();
    private final Label name = new Label();
    private final Label phone = new Label();
    private final ImageView imageView = new ImageView();
    private final Image image = new Image(getClass().getResource(PathHelper.CUSTOMER_IMAGE_FILE).toString());

    public CompanyListCell() {
        super();
        imageView.setFitWidth(45);
        imageView.setFitHeight(45);
        name.setFont(new Font(16));
        phone.setFont(new Font(14));
        infoBox.getChildren().addAll(name, phone);
        hBox.getChildren().addAll(imageView, infoBox);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(10);
        HBox.setHgrow(infoBox, Priority.ALWAYS);
    }

    @Override
    protected void updateItem(Company item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            name.setText((item != null) ? item.getCompanyName() : "<null>");
            phone.setText((item != null) ? item.getCompanyContact() : "<null>");
            imageView.setImage(image);
            setGraphic(hBox);
        } else {
            name.setText("");
            phone.setText("");
            setGraphic(null);
        }
    }

}