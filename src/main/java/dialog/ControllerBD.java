package dialog;

import basis.Main;
import database.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by 123 on 24.04.2016.
 */
public class ControllerBD implements Initializable {

    @FXML
    private Button ok;
    @FXML
    private javafx.scene.control.TextField login;
    @FXML
    private javafx.scene.control.PasswordField password;
    private DataBase dataBase;
    private Main main;
    private Stage dialogStage;
    String str;

    public void initialize(URL url, ResourceBundle rb) {


        ok.setOnAction(event -> {

            try {
                main.in(login.getText(), password.getText());
                dialogStage.close();
            } catch (SQLException e) {
                main.showError(e.getMessage());
            } catch (ClassNotFoundException e) {
                main.showError(e.getMessage());

            } catch (IOException e) {
                main.showError(e.getMessage());
            }

        });

    }


    public void setMain(Main main) {
        this.main = main;


    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;


    }

    public void setStage(Stage stage) {
        this.dialogStage = stage;


    }
}
