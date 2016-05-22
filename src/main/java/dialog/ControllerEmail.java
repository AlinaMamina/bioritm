package dialog;

import email.EmailMain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import basis.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEmail implements Initializable {

    @FXML
    private Button send;
    @FXML
    private javafx.scene.control.Label label;
    @FXML
    private javafx.scene.control.TextField text;
    private EmailMain email;
    private Main main;
    private Stage dialogStage;
    private ResourceBundle rb;
    String str;

    public void initialize(URL url, ResourceBundle rb) {


        send.setOnAction(event -> {

            try {
                email.send(text.getText(), str);
                dialogStage.close();

            } catch (javax.mail.MessagingException e) {
                main.showError(rb.getString("internet"));
            }

        });

    }

    public void setEmail(EmailMain email, Double[][] info) {
        this.email = email;
        this.str = rb.getString("phys_b") + info[0][0].toString() +
                "%. " + rb.getString("emotion_b")+ info[0][1].toString()
                + "%. " + rb.getString("intel_b") + info[0][2].toString()
                + "%. " + rb.getString("all_b") + info[0][3].toString() + "%";
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setStage(Stage stage) {
        this.dialogStage = stage;
    }
    public void setRB(ResourceBundle rb){
        this.rb = rb;
    }
}