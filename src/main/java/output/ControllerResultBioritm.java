package output;

import basis.BaseButton;
import calculation.CalcBiorithms;
import calculation.Conversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import basis.Main;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;


public class ControllerResultBioritm implements Initializable {
    @FXML
    private Button do_it;
    @FXML
    private ComboBox period;
    @FXML
    private Button save;
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
    @FXML
    private Label i_info;
    @FXML
    private Label e_info;
    @FXML
    LineChart<String, Double> grafik;
    @FXML
    CategoryAxis x_area;
    private Conversion conversion;
    private Main main;
    private BaseButton baseButton;
    private ResourceBundle rb;


    public void initialize(URL url, ResourceBundle rb) {

        do_it.setOnAction(event -> {

            try {
                main.showTable(period.getValue());
            } catch (IOException e) {
                main.showError(e.getMessage());
            } catch (NullPointerException e) {
                main.showError(rb.getString("period"));

            }
        });


        save.setOnAction(event -> main.showEmailDialog());
        back.setOnAction(event -> baseButton.showBioritm());

    }

    public void paintGrafik(CalcBiorithms bioritm) {
        List<String> x = new ArrayList<>();
        List<Integer> period = new ArrayList<>();
        GregorianCalendar[] data = new GregorianCalendar[1];
        data[0] = new GregorianCalendar();

        data[0].set(bioritm.getData()[0].get(Calendar.YEAR), bioritm.getData()[0].get(Calendar.MONTH), bioritm.getData()[0].get(Calendar.DAY_OF_MONTH));
        int p = bioritm.getPeriod();

        x_area.setLabel(rb.getString("_date"));

        for (int i = 0; i < 30; i++) {

            String[] str = conversion.dataToString(data, 1);
            x.add(i, str[0]);
            data[0].add(Calendar.DATE, 1);
            period.add(i, p + i);
        }


        Function<Integer, Double> calc1 = i -> bioritm.getPhysicalBiorhythms(i);
        Function<Integer, Double> calc2 = i -> bioritm.getEmotionalBiorhythms(i);
        Function<Integer, Double> calc3 = i -> bioritm.getIntellectualBiorhythms(i);
        Function<Integer, Double> calc4 = i -> bioritm.getAllBiorhythms(i);

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();
        XYChart.Series<String, Double> series3 = new XYChart.Series<>();
        XYChart.Series<String, Double> series4 = new XYChart.Series<>();

        for (Integer i : period) {
            period.indexOf(i);

            series1.getData().add(new XYChart.Data(x.get(period.indexOf(i)), calc1.apply(i)));
            series2.getData().add(new XYChart.Data(x.get(period.indexOf(i)), calc2.apply(i)));
            series3.getData().add(new XYChart.Data(x.get(period.indexOf(i)), calc3.apply(i)));
            series4.getData().add(new XYChart.Data(x.get(period.indexOf(i)), calc4.apply(i)));
        }

        series1.setName(rb.getString("phys"));
        series2.setName(rb.getString("emotion"));
        series3.setName(rb.getString("intel"));
        series4.setName(rb.getString("all"));

        grafik.getData().addAll(series1, series2, series3, series4);


    }


    public void setFields(Conversion conversion, BaseButton baseButton, Main main, ResourceBundle rb) {
        this.conversion = conversion;
        this.baseButton = baseButton;
        this.main = main;
        this.rb = rb;

    }

    public void setBioritm(Double[][] result) {
        phys.setText(result[0][0].toString() + "%");
        emotion.setText(result[0][1].toString() + "%");
        intel.setText(result[0][2].toString() + "%");
        all.setText(result[0][3].toString() + "%");
        setInfo(result);
    }

    private void setInfo(Double[][] result) {
        final String[] info_p = {rb.getString("info_p_good"), rb.getString("info_p_norm"),
                rb.getString("info_p_bad")};
        final String[] info_e = {rb.getString("info_e_good"), rb.getString("info_e_norm"),
                rb.getString("info_e_bad")};
        final String[] info_i = {rb.getString("info_i_good"), rb.getString("info_i_norm"),
                rb.getString("info_i_bad")};

        if (result[0][0] >= 40)
            p_info.setText(info_p[0]);
        if (result[0][0] > -30 && result[0][0] < 40)
            p_info.setText(info_p[1]);
        if (result[0][0] <= -30)
            p_info.setText(info_p[2]);

        if (result[0][1] >= 40)
            e_info.setText(info_e[0]);
        if (result[0][1] > -30 && result[0][1] < 40)
            e_info.setText(info_e[1]);
        if (result[0][1] <= -30)
            e_info.setText(info_e[2]);

        if (result[0][2] >= 40)
            i_info.setText(info_i[0]);
        if (result[0][2] > -30 && result[0][2] < 40)
            i_info.setText(info_i[1]);
        if (result[0][2] <= -30)
            i_info.setText(info_i[2]);
    }


}