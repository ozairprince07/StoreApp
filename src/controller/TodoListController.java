package controller;

import helper.ViewHelper;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TodoListController implements Initializable {

    public AnchorPane addTaskPane;

    public Button addBtn;

    public ToggleButton allBtn,
            staredBtn,
            importantBtn,
            doneBtn,
            trashedBtn;

    ViewHelper viewHelper = new ViewHelper();

    int currentIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBtnActions();
    }

    public void unCheck() {
        if (currentIndex != 0) allBtn.setSelected(false);
        if (currentIndex != 1) staredBtn.setSelected(false);
        if (currentIndex != 2) importantBtn.setSelected(false);
        if (currentIndex != 3) doneBtn.setSelected(false);
        if (currentIndex != 4) trashedBtn.setSelected(false);
    }

    public void setBtnActions() {
        allBtn.setOnAction(e -> {
            currentIndex = 0;
            unCheck();
        });

        staredBtn.setOnAction(e -> {
            currentIndex = 1;
            unCheck();
        });

        importantBtn.setOnAction(e -> {
            currentIndex = 2;
            unCheck();
        });

        doneBtn.setOnAction(e -> {
            currentIndex = 3;
            unCheck();
        });

        trashedBtn.setOnAction(e -> {
            currentIndex = 4;
            unCheck();
        });

        addBtn.setOnAction(e -> {
            addTaskPane.setVisible(true);
        });

    }

}
