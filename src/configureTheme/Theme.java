package configureTheme;

import javafx.scene.Parent;

import java.net.URL;

public class Theme {

    public static void setTheme(Parent root, URL cssPath) {
        root.getStylesheets().clear();
        root.getStylesheets().add(String.valueOf(cssPath));
    }

}
