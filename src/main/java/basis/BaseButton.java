package basis;

import button.Controller;
import button.ControllerCopatibility;
import button.ControllerHelp;
import button.ControllerSettings;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by 123 on 24.04.2016.
 */
public class BaseButton {
    private Stage primaryStage;
    private SplitPane rootLayout;
    private Main main;

    public BaseButton(Stage primaryStage, SplitPane rootLayout, Main main) {
        this.primaryStage = primaryStage;
        this.rootLayout = rootLayout;
        this.main = main;
    }

    public void showBioritm() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/button/bioritm.fxml"));
            AnchorPane BioritmLayout = (AnchorPane) loader.load();
            rootLayout.getItems().set(2, BioritmLayout);

            Controller controller = loader.getController();
            controller.setMain(main);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCopatibility() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/button/compatibility.fxml"));
            AnchorPane Layout = (AnchorPane) loader.load();

            rootLayout.getItems().set(2, Layout);

            ControllerCopatibility controller = loader.getController();
            controller.setMain(main);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInfo(String name) {
        try {
            AnchorPane AboutLayout = FXMLLoader.load(getClass().getResource(name));

            rootLayout.getItems().set(2, AboutLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSettings() {
        try {
            FXMLLoader loader = new FXMLLoader();
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
