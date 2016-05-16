package dialog;

import basis.Main;
import calculation.Conversion;
import database.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;


public class ControllerRegistration implements Initializable {

    @FXML
    private Button do_it;
    @FXML
    private TextField login;
    @FXML
    private javafx.scene.control.PasswordField password;
    @FXML
    private ComboBox day;
    @FXML
    private ComboBox month;
    @FXML
    private ComboBox year;
    private DataBase dataBase;
    private Main main;
    private Stage dialogStage;
    private Conversion conversion;
    String str;

    public void initialize(URL url, ResourceBundle rb) {


        do_it.setOnAction(event -> {

            try {

                main.registr(login.getText(), password.getText(), converte(day.getValue(), month.getValue(), year.getValue()));
                dialogStage.close();
            } catch (ClassNotFoundException e) {
                main.showError(e.getMessage());
            } catch (SQLException e) {
                main.showError(e.getMessage());
            } catch (IOException e) {
                main.showError(e.getMessage());
            }
        });

    }

    private String converte(Object day, Object month, Object year) {
        try {
            GregorianCalendar[] data = new GregorianCalendar[1];
            data[0] = conversion.toCalendar(day, month, year);
            String[] birthday = conversion.dataToString(data, 1);
            return birthday[0];
        } catch (NullPointerException e) {
            main.showError("¬ведите дату");
            return null;

        } catch (IllegalArgumentException e) {
            main.showError("¬ведите другую дату");
            return null;
        }

    }

    public void setClass(Main main, Stage stage, Conversion conversion) {
        this.main = main;
        this.dialogStage = stage;
        this.conversion = conversion;


    }
}



