package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    public TextField inputField;
    public Label prevLabel;

    public Button zero, one, two, three, four, five, six, seven, eight, nine,
            point, equal, plus, minus, multiply, divide, backspace;

    double a = 0, b = 0, result = 0;
    char op;
    String num1 = "", num2 = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void getNumberBtnText(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (inputField.getText().equals("0") && btn.getText().equals("0") && inputField.getPromptText().equals("0")) {
            inputField.setPromptText("0");
        } else {
            String text = inputField.getText();
            text += btn.getText();
            inputField.setText(text);
        }
    }

    @FXML
    public void addOperation(ActionEvent event) {
        op = '+';
        num1 = inputField.getText();
        a = Double.parseDouble(inputField.getText());
        prevLabel.setText(num1 + " + ");
        inputField.clear();
    }

    @FXML
    public void minOperation(ActionEvent event) {
        op = '-';
        num1 = inputField.getText();
        a = Double.parseDouble(inputField.getText());
        prevLabel.setText(num1 + " - ");
        inputField.clear();
    }

    @FXML
    public void mulOperation(ActionEvent event) {
        op = '*';
        num1 = inputField.getText();
        a = Double.parseDouble(inputField.getText());
        prevLabel.setText(num1 + " * ");
        inputField.clear();
    }

    @FXML
    public void divOperation(ActionEvent event) {
        op = '/';
        num1 = inputField.getText();
        a = Double.parseDouble(inputField.getText());
        prevLabel.setText(num1 + " / ");
        inputField.clear();
    }

    @FXML
    public void evalOperation(ActionEvent event) {
        num2 = inputField.getText();
        b = Double.parseDouble(inputField.getText());
        prevLabel.setText(num1 + op + num2);
        result = operate(a, op, b);
        inputField.setText(String.valueOf(result));
    }

    @FXML
    public void modOperation(ActionEvent event) {
        if (!(inputField.getText().equals("")) || inputField.getText().equals("0")) {
            a = Double.parseDouble(inputField.getText());
            result = (a / 100) * a;
            prevLabel.setText(a + "%");
            inputField.setText(String.valueOf(result));
        }
    }

    @FXML
    public void sqrOperation(ActionEvent event) {
        a = Double.parseDouble(inputField.getText());
        prevLabel.setText(a + "*" + a);
        result = a * a;
        inputField.setText(String.valueOf(result));
    }

    @FXML
    public void sqrtOperation(ActionEvent event) {
        a = Double.parseDouble(inputField.getText());
        prevLabel.setText(2 + "_/" + a);
        result = Math.sqrt(a);
        inputField.setText(String.valueOf(result));
    }

    @FXML
    public void overOperation(ActionEvent event) {
        a = Double.parseDouble(inputField.getText());
        prevLabel.setText(1 + "/" + a);
        result = 1 / a;
        inputField.setText(String.valueOf(result));
    }

    @FXML
    public void negOperation(ActionEvent event) {
        double num = Double.parseDouble(inputField.getText());
        result = num * (-1);
        inputField.setText(String.valueOf(result));
    }

    @FXML
    public void facOperation(ActionEvent event) {
        a = Double.parseDouble(inputField.getText());
        prevLabel.setText(a + "!");
        result = factorial(a);
        String res = String.valueOf(result);
        if (res.length() > 9) {
            res = calE(res);
        }
        inputField.setText(res);
    }

    @FXML
    public void clearField(ActionEvent event) {
        inputField.clear();
        prevLabel.setText("");
    }

    @FXML
    public void removeChar(ActionEvent event) {
        if (!(inputField.getText().equals(""))) {
            String text = inputField.getText();
            inputField.setText(text.substring(0, text.length() - 1));
        }
    }

    @FXML
    public void alwaysOnTop(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setAlwaysOnTop(true);
    }

    private double operate(double a, char op, double b) {
        switch (op) {
            case '+':
                return Math.round(a + b);
            case '-':
                return Math.round(a - b);
            case '*':
                return Math.round(a * b);
            case '/':
                if (b != 0) {
                    return Math.round(a / b);
                } else {
                    return 0;
                }
        }
        return 0;
    }

    private double factorial(double x) {
        return (x == 0 || x == 1 ? 1 : x * factorial(x - 1));
    }

    private String calE(String res) {
        int e_index = res.indexOf('E');
        String temp = res.substring(0, e_index - 1);
        temp = temp.substring(0, 5);
        return temp + res.substring(e_index);
    }

}
