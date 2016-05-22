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
import java.util.*;


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
    private ResourceBundle rb;
    private DataBase d;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.rb = ResourceBundle.getBundle("Locale", new Locale("en"));
        this.primaryStage.setTitle(rb.getString("button.biorhythm"));

        emailSender = new EmailMain();
        bioritm = new CalcBiorithms();
        compatibility = new CalcCompatibility();
        conversion = new Conversion(rb);

        File file;
        if (!(file = new File("src\\main\\resources\\testbase.mv.db")).exists())
            throw new RuntimeException("File not found!");
        String str = file.getAbsolutePath();
        d = new DataBase(str.replace(".mv.db", ""));

        initRootLayout();

    }


    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(rb);
            loader.setLocation(Main.class.getResource("/button/menu.fxml"));
            rootLayout = loader.load();

            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.getScene().getStylesheets().add("style/blau.css");

            primaryStage.show();

            FXMLLoader loader_bio = new FXMLLoader();
            loader_bio.setResources(rb);
            loader_bio.setLocation(Main.class.getResource("/button/bioritm.fxml"));
            AnchorPane BioritmLayout = (AnchorPane) loader_bio.load();


            Controller controller = loader_bio.getController();
            controller.setFields(this, conversion, rb);
            rootLayout.getItems().add(2, BioritmLayout);
            baseButton = new BaseButton(this.primaryStage, rootLayout, this, this.conversion,this.rb);

            ControllerMenu controller_menu = loader.getController();
            controller_menu.setMain(baseButton);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showTable(Object p) throws NullPointerException, IOException {
        int period = Integer.valueOf(p.toString());
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(rb);
        loader.setLocation(Main.class.getResource("/result/resultTable.fxml"));
        AnchorPane Layout = (AnchorPane) loader.load();

        rootLayout.getItems().set(2, Layout);

        bioritm.calcResult(period, bioritm.getData()[0], bioritm.getBirthday());
        bioritm.calcBest(Integer.valueOf(p.toString()));

        ControllerTable controller = loader.getController();
        controller.setMain(this);
        controller.setInfo(bioritm, conversion);

    }


    public void showResult(GregorianCalendar data1, GregorianCalendar data2) throws IOException, NullPointerException {
        bioritm.calcResult(1, data2, data1);
        Double[][] result = bioritm.getResult();

        FXMLLoader loader = new FXMLLoader();
        loader.setResources(rb);
        loader.setLocation(Main.class.getResource("/result/resultBioritm.fxml"));
        AnchorPane Layout = (AnchorPane) loader.load();

        rootLayout.getItems().set(2, Layout);


        ControllerResultBioritm controller = loader.getController();
        controller.setFields(conversion, baseButton, this, rb);
        controller.setBioritm(result);
        controller.paintGrafik(bioritm);
    }

    public void showResult() throws IOException, NullPointerException {

        Double[][] result = bioritm.getResult();

        FXMLLoader loader = new FXMLLoader();
        loader.setResources(rb);
        loader.setLocation(Main.class.getResource("/result/resultBioritm.fxml"));
        AnchorPane Layout = (AnchorPane) loader.load();

        rootLayout.getItems().set(2, Layout);

        ControllerResultBioritm controller = loader.getController();
        controller.setFields(conversion, baseButton, this, rb);
        controller.setBioritm(result);
        controller.paintGrafik(bioritm);

    }

    public void showResultCopatibility(GregorianCalendar bd1, GregorianCalendar bd2) throws IOException, NullPointerException {

        Double[] result = compatibility.calcCompatibility(bd1, bd2);

        FXMLLoader loader = new FXMLLoader();
        loader.setResources(rb);
        loader.setLocation(Main.class.getResource("/result/resultCopatibility.fxml"));
        AnchorPane Layout = (AnchorPane) loader.load();

        rootLayout.getItems().set(2, Layout);

        ControllerResultCop controller = loader.getController();

        controller.setBaseButton(baseButton);
        controller.setRB(rb);
        controller.setMain(this);
        controller.setCompatibility(result);

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
        if(language.toString().equals(rb.getString("russian"))) {
            this.rb = ResourceBundle.getBundle("Locale", new Locale("ru"));
        }
        if(language.toString().equals(rb.getString("english"))) {
            this.rb = ResourceBundle.getBundle("Locale", new Locale("en"));
        }



    }

    public void showEmailDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(rb);
            loader.setLocation(Main.class.getResource("/dialog/email.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ControllerEmail controller = loader.getController();
            //  controller.setDialogStage(dialogStage);

            controller.setMain(this);
            controller.setRB(rb);
            controller.setStage(dialogStage);
            controller.setEmail(emailSender, bioritm.getResult());
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void showDialogHelp() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(rb);
            loader.setLocation(Main.class.getResource("/dialog/sendMassage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();


            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ControllerSendM controller = loader.getController();
            controller.setEmail(emailSender);
            controller.setMain(this);
            controller.setRB(rb);
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
            loader.setResources(rb);
            loader.setLocation(Main.class.getResource("/dialog/BD.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
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
            loader.setResources(rb);
            loader.setLocation(Main.class.getResource("/dialog/registration.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(rb.getString("reg_form"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            ControllerRegistration controller = loader.getController();
            controller.setClass(this, dialogStage, conversion,rb);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public void showError(String text) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(rb);
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
            throw new ClassNotFoundException(rb.getString("not_found"));

        if (!d.HasLogin(u))
            throw new ClassNotFoundException(rb.getString("wrong_password"));
        d.Get_user_data(u);

        FXMLLoader loader = new FXMLLoader();
        loader.setResources(rb);
        loader.setLocation(Main.class.getResource("/button/bioritmWithUser.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        rootLayout.getItems().set(2, page);
        ControllerUserBioritm controller = loader.getController();
        controller.setInfo(u.login, u.date, u.birthday);
        controller.setFields(this,conversion,rb,baseButton);

        GregorianCalendar[] date = new GregorianCalendar[1];
        date[0] = new GregorianCalendar();
        String [] s = conversion.dataToString(date,1);

        d.Change_data_last_call(u, s[0]);
        d.Disconnect();
    }

    public void registr(String login, String password,String birthday) throws SQLException, ClassNotFoundException, IOException {
        User u = new User();

        u.login = login;
        u.password = password;
        u.birthday = birthday;
        GregorianCalendar[] date = new GregorianCalendar[1];
        date[0] = new GregorianCalendar();
        String [] s = conversion.dataToString(date,1);
        u.date = s[0];
        d.Connect();

        if (!d.HasUser(u)) {
            d.Add_user(u);
        } else
            throw new SQLException(rb.getString("user"));

        d.Disconnect();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }


}