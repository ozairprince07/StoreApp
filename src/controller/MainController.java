package controller;

import configureTheme.Theme;
import customWidgets.CustomDialog;
import helper.PathHelper;
import helper.ViewHelper;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public BorderPane borderPane;

    public ToggleButton menuBtn,
            dashboardBtn,
            invoiceBtn,
            exploreBtn,
            customerBtn,
            companyBtn,
            todoBtn,
            historyBtn,
            settingBtn,
            calculatorBtn,
            themeBtn;

    public Button openDialog;

    ViewHelper viewHelper = new ViewHelper();

    int currentIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuBtn.setSelected(true);
        dashboardBtn.setSelected(true);
        try {
            viewHelper.loadFxml(borderPane, PathHelper.DASHBOARD_FILE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        btnEvents();

    }

    private void setContentDisplay(ContentDisplay display) {
        dashboardBtn.setContentDisplay(display);
        invoiceBtn.setContentDisplay(display);
        exploreBtn.setContentDisplay(display);
        customerBtn.setContentDisplay(display);
        companyBtn.setContentDisplay(display);
        todoBtn.setContentDisplay(display);
        historyBtn.setContentDisplay(display);
        settingBtn.setContentDisplay(display);
    }

    private void btnEvents() {
        dashboardBtn.setOnAction(e -> {
            try {
                viewHelper.loadFxml(borderPane, PathHelper.DASHBOARD_FILE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            currentIndex = 0;
            uncheck();
            if (currentIndex == 0) {
                dashboardBtn.setSelected(true);
            }
        });

        invoiceBtn.setOnAction(e -> {
            try {
                viewHelper.loadFxml(borderPane, PathHelper.INVOICE_FILE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            currentIndex = 1;
            uncheck();
            if (currentIndex == 1) {
                invoiceBtn.setSelected(true);
            }
        });

        exploreBtn.setOnAction(e -> {
            try {
                viewHelper.loadFxml(borderPane, PathHelper.EXPLORE_FILE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            currentIndex = 2;
            uncheck();
            if (currentIndex == 2) {
                exploreBtn.setSelected(true);
            }
        });

        customerBtn.setOnAction(e -> {
            try {
                viewHelper.loadFxml(borderPane, PathHelper.CUSTOMER_FILE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            currentIndex = 3;
            uncheck();
            if (currentIndex == 3) {
                customerBtn.setSelected(true);
            }
        });

        companyBtn.setOnAction(e -> {
            try {
                viewHelper.loadFxml(borderPane, PathHelper.COMPANY_FILE);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            currentIndex = 4;
            uncheck();
            if (currentIndex == 4) {
                companyBtn.setSelected(true);
            }
        });

        todoBtn.setOnAction(e -> {
            try {
                viewHelper.loadFxml(borderPane, PathHelper.TODOAPP_FILE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            currentIndex = 5;
            uncheck();
            if (currentIndex == 5) {
                todoBtn.setSelected(true);
            }
        });

        historyBtn.setOnAction(e -> {
            currentIndex = 6;
            uncheck();
            if (currentIndex == 6) {
                historyBtn.setSelected(true);
            }
        });

        settingBtn.setOnAction(e -> {
            try {
                viewHelper.loadFxml(borderPane, PathHelper.SETTING_FILE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            currentIndex = 7;
            uncheck();
            if (currentIndex == 7) {
                settingBtn.setSelected(true);
            }
        });

        menuBtn.setOnAction(e -> {
            if (menuBtn.isSelected()) {
                setContentDisplay(ContentDisplay.LEFT);
            } else {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        });

        calculatorBtn.setOnAction(e -> {
            String OS = System.getProperty("os.name");
            System.out.println(OS);
            if (OS.indexOf("Windows") >= 0) {
                try {
                    Process p = Runtime.getRuntime().exec("calc");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else if (OS.indexOf("nux") >= 0) {
                try {
                    Process p = Runtime.getRuntime().exec("qalculate-gtk");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

//            try {
//                viewHelper.loadCal();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }

        });

        themeBtn.setOnAction(e -> {
            Parent root = themeBtn.getParent().getScene().getRoot();
            if (themeBtn.isSelected()) {
                Theme.setTheme(root, getClass().getResource(PathHelper.LIGHT));
            } else {
                Theme.setTheme(root, getClass().getResource(PathHelper.DARK));
            }
        });

        openDialog.setOnAction(e -> {
            try {
                CustomDialog customDialog = new CustomDialog(PathHelper.CALCULATOR_FILE);
                customDialog.showDialog();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }

    private void uncheck() {
        if (currentIndex != 0) dashboardBtn.setSelected(false);
        if (currentIndex != 1) invoiceBtn.setSelected(false);
        if (currentIndex != 2) exploreBtn.setSelected(false);
        if (currentIndex != 3) customerBtn.setSelected(false);
        if (currentIndex != 4) companyBtn.setSelected(false);
        if (currentIndex != 5) todoBtn.setSelected(false);
        if (currentIndex != 6) historyBtn.setSelected(false);
        if (currentIndex != 7) settingBtn.setSelected(false);
    }

}
