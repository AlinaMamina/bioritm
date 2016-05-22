package button;

import basis.BaseButton;
import basis.Main;
import calculation.CalcBiorithms;
import calculation.Conversion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
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
    private Conversion conversion;
    private ResourceBundle rb;


    public void initialize(URL url, ResourceBundle rb) {

        do_it.setOnAction(event -> {
                    try {
                        GregorianCalendar birthday = conversion.toCalendar(bd_day.getValue(), bd_month.getValue(), bd_year.getValue());
                        GregorianCalendar data = conversion.toCalendar(day.getValue(), month.getValue(), year.getValue());
                        if (birthday.after(data)) {
                            main.showError(rb.getString("wrong_date"));
                            return;
                        }
                        main.showResult(birthday, data);
                    } catch (NullPointerException e) {
                        main.showError(rb.getString("enter_date"));

                    } catch (IllegalArgumentException e) {
                        main.showError(rb.getString("enter_another"));
                    } catch (IOException e) {
                        e.printStackTrace();
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

    public void setFields(Main main,Conversion conversion,ResourceBundle rb,BaseButton baseButton) {
        this.main = main;
        this.conversion = conversion;
        this.rb = rb;
        this.baseButton = baseButton;

    }
}