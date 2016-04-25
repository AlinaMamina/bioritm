package button;

import basis.BaseButton;
import calculation.CalcBiorithms;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import basis.Main;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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
    private Button signIn;
    @FXML
    private Button registr;
    @FXML
    private Label error;
    private Main main;
    private CalcBiorithms bioritm;

    public void initialize(URL url, ResourceBundle rb) {


        do_it.setOnAction(event -> {
            try {
                main.showResult(bd_day.getValue(), bd_month.getValue(), bd_year.getValue(), day.getValue(), month.getValue(), year.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                main.showError("Введите дату");

            }
        });

        signIn.setOnAction(event -> main.showSignInDialog());
        registr.setOnAction(event -> main.showRegistrationDialog());
    }

    public void setMain(Main main) {
        this.main = main;


    }

    public void setBioritm(CalcBiorithms bioritm) {
        this.bioritm = bioritm;

    }
}
