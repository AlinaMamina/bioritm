package button;

import basis.BaseButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import basis.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by 123 on 15.04.2016.
 */
public class ControllerHelp implements Initializable {
    @FXML
    private Button help;
    @FXML
    private Button what;
    private Main main;

    private BaseButton baseButton;

    public void initialize(URL url, ResourceBundle rb) {
        help.setOnAction(event -> main.showDialogHelp());
        what.setOnAction(event -> baseButton.showInfo("/result/tutorial.fxml"));
    }

    public void setMain(Main main) {
        this.main = main;

    }

    public void setBaseButton(BaseButton baseButton) {
        this.baseButton = baseButton;
    }
}

