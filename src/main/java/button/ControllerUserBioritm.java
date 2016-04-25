package button;

import basis.BaseButton;
import basis.Main;
import calculation.CalcBiorithms;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerUserBioritm implements Initializable {
    @FXML
    private Button do_it;
    @FXML
    private ComboBox bd_day;
    @FXML
    private ComboBox bd_month;
    @FXML
    private ComboBox bd_year;
    @FXML
    private ComboBox day;
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox year;
    @FXML
    private Button exit;
    @FXML
    private Label error;
    @FXML
    private Label data;
    @FXML
    private Label user;

    private Main main;
    private BaseButton baseButton;

    public void initialize(URL url, ResourceBundle rb) {

        do_it.setOnAction(event -> {
            try {
                main.showResult(bd_day.getValue(), bd_month.getValue(), bd_year.getValue(), day.getValue(), month.getValue(), year.getValue());
            } catch (IOException e) {
                error.setText("Введите дату!");
            }
        });

        exit.setOnAction(event -> baseButton.showBioritm());

    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setInfo(String data, String user, String bd) {
        String[] str = bd.split("\\.");
        this.data.setText(data);
        this.user.setText(user);
        bd_day.setValue(str[0]);
        bd_month.setValue(str[1]);
        bd_year.setValue(str[2]);
    }

    public void setbaseButton(BaseButton baseButton) {
        this.baseButton = baseButton;

    }
}