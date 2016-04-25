package output;

import calculation.CalcBiorithms;
import calculation.Conversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import basis.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class ControllerTable implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Label phys;
    @FXML
    private Label emotion;
    @FXML
    private Label intel;
    @FXML
    private javafx.scene.control.TableColumn<Table, String> day;
    @FXML
    private javafx.scene.control.TableColumn<Table, String> result;
    @FXML
    private TableView<Table> table;


    private Main main;
    private CalcBiorithms bioritm;

    private ObservableList<Table> resultTable = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {

        back.setOnAction(event -> {
            try {
                main.showResult();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setMain(Main main) {
        this.main = main;

    }

    public void setInfo(CalcBiorithms bioritm, Conversion conversion) {
        this.bioritm = bioritm;
        String[] str = conversion.dataToString(bioritm.getBest(), 3);
        phys.setText(str[0]);
        emotion.setText(str[1]);
        intel.setText(str[2]);

        String[] s = conversion.dataToString(bioritm.getData(), bioritm.getCount());
        for (int i = 0; i < bioritm.getCount(); i++) {
            resultTable.add(new Table(s[i], conversion.bioritmToString(bioritm.getResult()[i])));
        }
        day.setCellValueFactory(new PropertyValueFactory<Table, String>("data"));
        result.setCellValueFactory(new PropertyValueFactory<Table, String>("result"));
        table.setItems(resultTable);
    }
}
