package button;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import basis.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by 123 on 11.04.2016.
 */
public class ControllerSettings implements Initializable {

    @FXML
    private ComboBox sprache;
    @FXML
    private ComboBox style;
    @FXML
    private Button do_it;
    private Main main;

    public void initialize(URL url, ResourceBundle rb) {
        do_it.setOnAction(event -> main.chooseSettings(style.getValue(), sprache.getValue()));

    }

    public void setMain(Main main) {
        this.main = main;

    }
}
