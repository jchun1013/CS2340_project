package Controller;

import Model.Location;
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
public class viewSourceController implements Initializable {
    private ObservableList<WaterSourceReport> data;
    private ConnectDB dc = new ConnectDB();

    @FXML
    private TableView<WaterSourceReport> sourceTable;
    @FXML
    private TableColumn<WaterSourceReport, String> col_reportNumber;
    @FXML
    private TableColumn<WaterSourceReport, String> col_dateTime;
    @FXML
    private TableColumn<WaterSourceReport, String> col_name;
    @FXML
    private TableColumn<WaterSourceReport, String> col_type;
    @FXML
    private TableColumn<WaterSourceReport, String> col_condition;
    @FXML
    private TableColumn<WaterSourceReport, String> col_longi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection conn = dc.getConnection();
            data = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM source_report");
            while (rs.next()) {
                //get string from db,whichever way
                data.add(new WaterSourceReport(rs.getString(2), rs.getInt(1), rs.getString(3)
                , new Location(rs.getDouble(7), rs.getDouble(6)), rs.getString(5), rs.getString(4)));
                System.out.println(new WaterSourceReport(rs.getString(2), rs.getInt(1), rs.getString(3)
                        , new Location(rs.getDouble(7), rs.getDouble(6)), rs.getString(5), rs.getString(4)).toString());
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }

        col_reportNumber.setCellValueFactory(new PropertyValueFactory<>("reportNumber"));
        col_dateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("nameOfReporter"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("waterType"));
        col_condition.setCellValueFactory(new PropertyValueFactory<>("condition"));
        col_longi.setCellValueFactory(new PropertyValueFactory<>("location"));

        sourceTable.setItems(null);
        sourceTable.setItems(data);
    }
}
