package basis;

import button.ControllerUserBioritm;
import calculation.CalcBiorithms;
import calculation.CalcCompatibility;
import calculation.Conversion;
import database.DataBase;
import database.User;
import dialog.*;
import email.EmailMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;


import button.Controller;
import output.ControllerResultBioritm;
import output.ControllerResultCop;
import output.ControllerTable;


public class Main extends Application {


    private Stage primaryStage;
    private SplitPane rootLayout;
    private EmailMain emailSender;
    private CalcBiorithms bioritm;
    private CalcCompatibility compatibility;
    private Conversion conversion;
    private BaseButton baseButton;

    private DataBase d;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Bioritm");

        emailSender = new EmailMain();
        bioritm = new CalcBiorithms();
        compatibility = new CalcCompatibility();
        conversion = new Conversion();
        File file;
        if (!(file = new File("src\\main\\resources\\testbase.mv.db")).exists())
            throw new RuntimeException("File not found!");
        String str = file.getAbsolutePath();
        d = new DataBase(str.replace(".mv.db",""));
        initRootLayout();

    }


    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/button/menu.fxml"));
            rootLayout = loader.load();

            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.getScene().getStylesheets().add("style/blau.css");

            primaryStage.show();

            FXMLLoader loader_bio = new FXMLLoader();
            loader_bio.setLocation(Main.class.getResource("/button/bioritm.fxml"));
            AnchorPane BioritmLayout = (AnchorPane) loader_bio.load();


            Controller controller = loader_bio.getController();
            controller.setMain(this);
            rootLayout.getItems().add(2, BioritmLayout);
            baseButton = new BaseButton(this.primaryStage, rootLayout, this);

            ControllerMenu controller_menu = loader.getController();
            controller_menu.setMain(baseButton);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showTable(Object p) throws NullPointerException, IOException {
        int period = Integer.valueOf(p.toString());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/result/resultTable.fxml"));
        AnchorPane Layout = (AnchorPane) loader.load();

        rootLayout.getItems().set(2, Layout);

        bioritm.calcResult(period, bioritm.getData()[0], bioritm.getBirthday());
        bioritm.calcBest(Integer.valueOf(p.toString()));

        ControllerTable controller = loader.getController();
        controller.setMain(this);
        controller.setInfo(bioritm, conversion);

    }


    public void showResult(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6) throws IOException, NullPointerException {

        GregorianCalendar data1 = conversion.toCalendar(s1, s2, s3);
        GregorianCalendar data2 = conversion.toCalendar(s4, s5, s6);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/result/resultBioritm.fxml"));
        AnchorPane Layout = (AnchorPane) loader.load();

        rootLayout.getItems().set(2, Layout);
        bioritm.calcResult(1, data1, data2);
        Double[][] result = bioritm.getResult();
        ControllerResultBioritm controller = loader.getController();
        controller.setBioritm(result);
        controller.setMain(this);
        controller.setBaseButton(baseButton);
      //  controller.paintGrafik(bioritm);
    }

    public void showResult() throws IOException, NullPointerException {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/result/resultBioritm.fxml"));
        AnchorPane Layout = (AnchorPane) loader.load();

        rootLayout.getItems().set(2, Layout);
        Double[][] result = bioritm.getResult();
        ControllerResultBioritm controller = loader.getController();
        controller.setBioritm(result);
        controller.setBaseButton(baseButton);
        controller.setMain(this);

    }

    public void showResultCopatibility(Object s1, Object s2, Object s3, Object s4, Object s5, Object s6) throws IOException, NullPointerException {

        GregorianCalendar bd1 = conversion.toCalendar(s1, s2, s3);
        GregorianCalendar bd2 = conversion.toCalendar(s4, s5, s6);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/result/resultCopatibility.fxml"));
        AnchorPane Layout = (AnchorPane) loader.load();

        Double[] result = compatibility.calcCompatibility(bd1, bd2);
        rootLayout.getItems().set(2, Layout);

        ControllerResultCop controller = loader.getController();
        controller.setCompatibility(result);
        controller.setBaseButton(baseButton);
        controller.setMain(this);

    }

    public void chooseSettings(Object style, Object language) {

        if (style.toString().equals("red"))
            primaryStage.getScene().getStylesheets().setAll("/style/red.css");

        if (style.toString().equals("blue"))
            primaryStage.getScene().getStylesheets().setAll("/style/blau.css");
        if (style.toString().equals("green"))
            primaryStage.getScene().getStylesheets().setAll("/style/green.css");

        if (style.toString().equals("black"))
            primaryStage.getScene().getStylesheets().setAll("/style/black.css");


    }

    public void showEmailDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/dialog/email.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Send result");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ControllerEmail controller = loader.getController();
            //  controller.setDialogStage(dialogStage);
            controller.setEmail(emailSender, bioritm.getResult());
            controller.setMain(this);
            controller.setStage(dialogStage);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void showDialogHelp() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/dialog/sendMassage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();


            Stage dialogStage = new Stage();
            dialogStage.setTitle("Send message");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ControllerSendM controller = loader.getController();
            controller.setEmail(emailSender);
            controller.setMain(this);
            controller.setStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }


    public void showSignInDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/dialog/BD.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("In");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            ControllerBD controller = loader.getController();
            controller.setMain(this);
            controller.setStage(dialogStage);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void showRegistrationDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/dialog/registration.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Registration form");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            ControllerRegistration controller = loader.getController();
            controller.setMain(this);
            controller.setStage(dialogStage);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void showError(String text) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/dialog/error.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Error");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ControllerError controller = loader.getController();
            controller.setText(text);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void in(String login, String password) throws SQLException, ClassNotFoundException, IOException {
        User u = new User();

        u.login = login;
        u.password = password;
        d.Connect();

        if (!d.HasUser(u))
            throw new ClassNotFoundException("User not found");


        d.Get_user_data(u);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/button/bioritmWithUser.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        rootLayout.getItems().set(2, page);
        ControllerUserBioritm controller = loader.getController();
        controller.setInfo(u.login, u.date, u.birthday);
        controller.setMain(this);
        controller.setbaseButton(baseButton);

        d.Change_data_last_call(u, "25.04.2016");
        d.Disconnect();
    }

    public void registr(String login, String password, String birthday) throws SQLException, ClassNotFoundException, IOException {
        User u = new User();

        u.login = login;
        u.password = password;
        u.birthday = birthday;
        //Calendar calendar = Calendar.getInstance();
        u.date = "22.04.2016";
        d.Connect();


        if (!d.HasLogin(u)) {
            //  if (u.birthday.length() > 10 || u.birthday.length() < 8 || u.birthday.indexOf(".") == -1)
            //     throw new IOException("Введите дату в формате dd.mm.yyyy");
            d.Add_user(u);
        } else
            throw new SQLException("User exist");


        d.Disconnect();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }


}