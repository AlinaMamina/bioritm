package dialog;

import email.EmailMain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import basis.Main;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSendM implements Initializable {

    @FXML
    private Button send;
    @FXML
    private javafx.scene.control.TextArea text;
    private EmailMain email;
    private Main main;
    private Stage dialogStage;

    public void initialize(URL url, ResourceBundle rb) {

        send.setOnAction(event -> {
            try {
                email.send("alinamalina555@yandex.ru", text.getText());
                this.dialogStage.close();
            } catch (MessagingException e) {
                main.showError("Отсутствует подключение к интернету");
            }
        });

    }

    public void setEmail(EmailMain email) {
        this.email = email;

    }

    public void setMain(Main main) {
        this.main = main;


    }

    public void setStage(Stage stage) {
        this.dialogStage = stage;


    }
}