package output;

import basis.BaseButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import basis.Main;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerResultCop implements Initializable {


    @FXML
    private Button back;
    @FXML
    private Label phys;
    @FXML
    private Label emotion;
    @FXML
    private Label intel;
    @FXML
    private Label all;
    @FXML
    private Label p_info;
    Main main;

    private BaseButton baseButton;
    final String[] info = {"Такие значения совместимости говорят об отличных взаимоотношениях.", "Все очень неоднозначно, возможно у вас сможет что-то получиться", "Ваши биоритмы не совместимы"};

    public void initialize(URL url, ResourceBundle rb) {
        back.setOnAction(event -> baseButton.showCopatibility());
    }
   public void setMain(Main main) {
        this.main = main;

    }

    public void setCompatibility(Double[] result) {

        phys.setText(result[0].toString() + "%");
        emotion.setText(result[1].toString() + "%");
        intel.setText(result[2].toString() + "%");
        all.setText(result[3].toString() + "%");
        if (result[3] >= 80)
            p_info.setText(info[0]);
        if (result[3] > 30 && result[3] < 80)
            p_info.setText(info[1]);
        if (result[3] <= 30)
            p_info.setText(info[1]);

    }


    public void setBaseButton(BaseButton baseButton) {
        this.baseButton = baseButton;
    }
}