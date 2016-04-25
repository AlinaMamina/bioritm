package dialog;

import basis.Main;
import database.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerError implements Initializable {

    @FXML
    private Label error;

    public void initialize(URL url, ResourceBundle rb) {

    }


    public void setText(String text) {
        error.setText(text);


    }
}
