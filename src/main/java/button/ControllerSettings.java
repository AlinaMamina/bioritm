package button;

import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import basis.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by 123 on 11.04.2016.
 */
public class ControllerSettings implements Initializable {

    @FXML
    private ComboBox<String> sprache;
    @FXML
    private ComboBox<String>  style;
    @FXML
    private Button do_it;
    private Main main;
    private ResourceBundle rb;

    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList<String> lang =
                FXCollections.observableArrayList(
                        new String(rb.getString("english")),
                        new String(rb.getString("russian")));
        sprache.setItems(lang);
        final ObservableList<String> style_val =
                FXCollections.observableArrayList(
                        new String(rb.getString("blue")),
                        new String(rb.getString("green")),
                new String(rb.getString("red")),
                new String(rb.getString("black")));
        style.setItems(style_val);
        do_it.setOnAction(event -> {
            try {
                main.chooseSettings(style.getValue(), sprache.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void setMain(Main main) {
        this.main = main;

    }
    public void setRB(ResourceBundle rb){
        this.rb = rb;
    }
}
