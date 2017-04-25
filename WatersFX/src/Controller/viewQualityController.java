package Controller;

import Model.Location;
import Model.WaterPurityReport;
import Model.WaterSourceReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by joon1 on 2017-04-25.
 */
public class viewQualityController implements Initializable {
    private ObservableList<WaterPurityReport> data;
    private ConnectDB dc = new ConnectDB();

    @FXML
    private TableView table;
    @FXML
    private TableColumn<WaterPurityReport, String> col_reportNumber;
    @FXML
    private TableColumn<WaterPurityReport, String> col_dateTime;
    @FXML
    private TableColumn<WaterPurityReport, String> col_name;
    @FXML
    private TableColumn<WaterPurityReport, String> col_virus;
    @FXML
    private TableColumn<WaterPurityReport, String> col_contamin;
    @FXML
    private TableColumn<WaterPurityReport, String> col_condition;
    @FXML
    private TableColumn<WaterPurityReport, String> col_location;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection conn = dc.getConnection();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM purity_report");
            while (rs.next()) {
                //get string from db,whichever way
                data.add(new WaterPurityReport(rs.getString(1), rs.getInt(2), rs.getString(3)
                        , new Location(rs.getDouble(4), rs.getDouble(5)), rs.getString(6), rs.getDouble(7), rs.getDouble(8)));
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }

        col_reportNumber.setCellValueFactory(new PropertyValueFactory<>("reportNumber"));
        col_condition.setCellValueFactory(new PropertyValueFactory<>("condition"));
        col_dateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("nameOfWorker"));
        col_virus.setCellValueFactory(new PropertyValueFactory<>("virusPPM"));
        col_contamin.setCellValueFactory(new PropertyValueFactory<>("contaminantPPM"));
        col_location.setCellValueFactory(new PropertyValueFactory<>("location"));

        table.setItems(null);
        table.setItems(data);
    }
}
