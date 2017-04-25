package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by joon1 on 2017-04-24.
 */
public class SourceReportController implements Initializable{
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    private final String currentDateandTime = sdf.format(new Date());

    @FXML
    private Label reportNumber;
    @FXML
    private TextField dateAndTime;
    @FXML
    private ComboBox<String> waterCondition;
    @FXML
    private ComboBox<String> waterType;
    @FXML
    private TextField lati;
    @FXML
    private TextField longi;


    @FXML
    private void submitClicked() {
        submitToDatabase();
    }

    private void submitToDatabase() {
        try {
            PreparedStatement stmt = null;
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();



        } catch (SQLException e) {

        }
    }

    @FXML
    private void cancelClicked() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        waterCondition.getItems().setAll("Waste", "Treatable-Clear", "Treatable-Muddy", "Portable");
        waterType.getItems().setAll("Bottle", "Well", "Stream", "Lake", "Spring", "Other");
    }
}
