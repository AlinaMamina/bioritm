package button;

import calculation.Conversion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import basis.Main;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Created by 123 on 10.04.2016.
 */
public class ControllerCopatibility implements Initializable {
    @FXML
    private Button do_it;
    @FXML
    private ComboBox bd_day1;
    @FXML
    private ComboBox bd_month1;
    @FXML
    private ComboBox bd_year1;
    @FXML
    private ComboBox bd_day2;
    @FXML
    private ComboBox bd_month2;
    @FXML
    private ComboBox bd_year2;
    @FXML
    private Label error;
    private Main main;
    private Conversion conversion;
    private ResourceBundle rb;

    public void initialize(URL url, ResourceBundle rb) {

        do_it.setOnAction(event -> result());


    }

    private void result() {
        try {
            GregorianCalendar bd1 = conversion.toCalendar(bd_day1.getValue(), bd_month1.getValue(), bd_year1.getValue());
            GregorianCalendar bd2 = conversion.toCalendar(bd_day2.getValue(), bd_month2.getValue(), bd_year2.getValue());


            main.showResultCopatibility(bd1, bd2);
        } catch (IllegalArgumentException e) {
            main.showError(rb.getString("enter_another"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            main.showError(rb.getString("enter_date"));

        }

    }

    public void setFields(Main main,Conversion conversion,ResourceBundle rb) {
        this.main = main;
        this.conversion = conversion;
        this.rb = rb;

    }
}