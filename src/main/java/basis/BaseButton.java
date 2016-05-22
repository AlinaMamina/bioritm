package basis;

import button.Controller;
import button.ControllerCopatibility;
import button.ControllerHelp;
import button.ControllerSettings;
import calculation.Conversion;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by 123 on 24.04.2016.
 */
public class BaseButton {
    private Stage primaryStage;
    private SplitPane rootLayout;
    private Main main;
    private Conversion conversion;
    private ResourceBundle rb;

    public BaseButton(Stage primaryStage, SplitPane rootLayout, Main main, Conversion conversion, ResourceBundle rb) {
        this.primaryStage = primaryStage;
        this.rootLayout = rootLayout;
        this.main = main;
        this.conversion = conversion;
        this.rb = rb;
    }

    public void showBioritm() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setResources(rb);
            loader.setLocation(Main.class.getResource("/button/bioritm.fxml"));
            AnchorPane BioritmLayout = (AnchorPane) loader.load();
            rootLayout.getItems().set(2, BioritmLayout);

            Controller controller = loader.getController();
            controller.setFields(main, conversion, rb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCopatibility() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setResources(rb);
            loader.setLocation(Main.class.getResource("/button/compatibility.fxml"));
            AnchorPane Layout = (AnchorPane) loader.load();

            rootLayout.getItems().set(2, Layout);

            ControllerCopatibility controller = loader.getController();
            controller.setFields(main,conversion,rb);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInfo(String name) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(rb);
            loader.setLocation((Main.class.getResource(name)));
            AnchorPane AboutLayout = loader.load();
            rootLayout.getItems().set(2, AboutLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSettings() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(rb);
            loader.setLocation(Main.class.getResource("/button/settings.fxml"));
            AnchorPane Layout = (AnchorPane) loader.load();
            rootLayout.getItems().set(2, Layout);
            ControllerSettings controller = loader.getController();
            controller.setMain(main);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHelp() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(rb);
            loader.setLocation(Main.class.getResource("/button/help.fxml"));
            AnchorPane Layout = (AnchorPane) loader.load();
            rootLayout.getItems().set(2, Layout);

            ControllerHelp controller = loader.getController();
            controller.setMain(main);

            controller.setBaseButton(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
