package basis;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {
    @FXML
    private Button bioritm;
    @FXML
    private Button what;
    @FXML
    private Button copatibility;
    @FXML
    private Button settings;
    @FXML
    private Button help;
    private BaseButton baseButton;

    public void initialize(URL url, ResourceBundle rb) {
        bioritm.setOnAction(event -> baseButton.showBioritm());
        what.setOnAction(event -> baseButton.showInfo("/button/aboutBioritm.fxml"));
        copatibility.setOnAction(event -> baseButton.showCopatibility());
        settings.setOnAction(event -> baseButton.showSettings());
        help.setOnAction(event -> baseButton.showHelp());
    }

    public void setMain(BaseButton baseButton) {
        this.baseButton = baseButton;


    }
}
