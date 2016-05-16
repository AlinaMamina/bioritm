package button;

import basis.BaseButton;
import calculation.CalcBiorithms;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private Button today;
    @FXML
    private Label error;
    private Main main;
    private Conversion conversion;



    public void initialize(URL url, ResourceBundle rb) {

        do_it.setOnAction(event->result());
        signIn.setOnAction(event -> main.showSignInDialog());
        registr.setOnAction(event -> main.showRegistrationDialog());
        today.setOnAction(event->setData());
    }
private void setData(){
    GregorianCalendar[] date = new GregorianCalendar[1];
    date[0] = new GregorianCalendar();
    String [] s = conversion.dataToString(date,1);
    String[] str = s[0].split("\\.");

    day.setValue(str[0]);
    month.setValue(str[1]);
    year.setValue(str[2]);
}
    private void result()
    {
        try {
            GregorianCalendar birthday = conversion.toCalendar(bd_day.getValue(), bd_month.getValue(), bd_year.getValue());
            GregorianCalendar data = conversion.toCalendar(day.getValue(), month.getValue(), year.getValue());
            if (birthday.after(data)) {
                main.showError("дата введена неверно");
                return;
            }
            main.showResult(birthday,data);
        }catch (NullPointerException e) {
            main.showError("Введите дату");

        }catch (IllegalArgumentException e) {
            main.showError("Введите другую дату");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setMain(Main main) {
        this.main = main;


    }
    public void setConversion(Conversion conversion) {
        this.conversion = conversion;

    }

}
