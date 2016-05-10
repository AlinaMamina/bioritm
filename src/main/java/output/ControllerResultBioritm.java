package output;

import basis.BaseButton;
import calculation.CalcBiorithms;
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
    LineChart<Calendar, Double> grafik;

    private Main main;
    private BaseButton baseButton;
    final String[] info_p = {"Наблюдается хорошее физическое и физиологическое состояние," +
            "подъем сил, выносливость",
            "Все относительно, делайте то, что вам нравится!",
            "Повышается утомляемость, физические нагрузки, сильнее отражаются на организме. " +
                    "В эту фазу, рекомендуется внимательнее быть к своему здоровью, снизить активность и не переживать. "};
    final String[] info_e = {"Наблюдается улучшение настроения, оптимизм, реакция на эмоциональные" +
            " раздражители, существенно спокойней чем в другое время.",
            "Все относительно, страйтесь меньше нервничать и больше радоваться жизни",
            "Наблюдается пессимистичное настроение, апатия и вялость.  "};
    final String[] info_i = {"Легко усваивается информация, умственные способности на максимуме, " +
            "творческие процессы идут намного легче. ",
            "Все относительно, и зависит только от вашего настроя",
            "Ухудшается концентрация, интеллектуальные способности, повышается умственная утомляемость."};


    public void initialize(URL url, ResourceBundle rb) {

        do_it.setOnAction(event -> {

            try {
                main.showTable(period.getValue());
            } catch (IOException e) {
                main.showError(e.getMessage());
            } catch (NullPointerException e) {
                main.showError("Введите период");

            }
        });


        save.setOnAction(event -> main.showEmailDialog());
        back.setOnAction(event -> baseButton.showBioritm());

    }

    public void paintGrafik(CalcBiorithms bioritm) {
        List<Calendar> x = new ArrayList<>();
        List<Integer> period = new ArrayList<>();
        Calendar data = bioritm.getData()[0];
        int p = bioritm.getPeriod();

        for (int i = 0; i < 30; i++)
        {
            data.add(Calendar.DATE, 1);
            x.add(i, data);
            period.add(i, p+i);
        }



         Function<Integer, Double> calc1 = i -> bioritm.getPhysicalBiorhythms(i);
        // Function<Integer, Double> calc2 = i -> bioritm.getEmotionalBiorhythms(i);
       //  Function<Integer, Double> calc3 = i -> bioritm.getIntellectualBiorhythms(i);
         ObservableList<XYChart.Series<Calendar, Double>> data1 = FXCollections.observableArrayList();
       // ObservableList<XYChart.Series<Integer, Double>> data2 = FXCollections.observableArrayList();
       // ObservableList<XYChart.Series<Integer, Double>> data3 = FXCollections.observableArrayList();

            XYChart.Series<Calendar, Double> series1 = new XYChart.Series<>();
      //  XYChart.Series<Integer, Double> series2 = new XYChart.Series<>();
      //  XYChart.Series<Integer, Double> series3 = new XYChart.Series<>();
            for (Integer i : period) {
                series1.getData().add(new XYChart.Data(i, calc1.apply(i)));
         //       series2.getData().add(new XYChart.Data(i, calc2.apply(i)));
         //       series3.getData().add(new XYChart.Data(i, calc3.apply(i)));
            }

            data1.add(series1);
      //  data2.add(series2);
     //   data3.add(series3);



        grafik.setData(data1);
       // grafik.setData(data2);
        //grafik.setData(data3);
    }




    public void setMain(Main main) {
        this.main = main;

    }

    public void setBioritm(Double[][] result) {
        phys.setText(result[0][0].toString() + "%");
        emotion.setText(result[0][1].toString() + "%");
        intel.setText(result[0][2].toString() + "%");
        all.setText(result[0][3].toString() + "%");
        setInfo(result);
    }

    private void setInfo(Double[][] result) {
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

    public void setBaseButton(BaseButton baseButton) {
        this.baseButton = baseButton;
    }
}