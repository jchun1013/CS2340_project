package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static Controller.WelcomeScreen.currentUser;

/**
 * Created by joon1 on 2017-04-24.
 */
public class SourceReportController implements Initializable{
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    private final String currentDateandTime = sdf.format(new Date());
    private int count = 0;

    @FXML
    private Label reportNumber;
    @FXML
    private Label dateAndTime;
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
            PreparedStatement stmt2 = null;
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();

            if (lati.getText().isEmpty() || longi.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty TextField");
                alert.setContentText("Please fill in all text fields");
                alert.showAndWait();
            }

            String sql = "INSERT INTO source_report(`reportNumber`, `dateTime`, `name`, `type`, `condition`, `longitude`, `latitude`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";

           // String sql2 = "SELECT COUNT(*) FROM source_report";

            try {
//                stmt2 = conn.prepareStatement(sql2);
//                ResultSet rs = stmt2.executeQuery();
//
//                if (rs.next()) {
//                    System.out.println(rs.getInt(1));
//                    count = 1 + rs.getInt(1);
//                }
//                stmt2.close();

                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, count);
                stmt.setString(2, dateAndTime.getText());
                stmt.setString(3, currentUser.getName());
                stmt.setString(4, waterType.getSelectionModel().getSelectedItem().toString());
                stmt.setString(5, waterCondition.getSelectionModel().getSelectedItem().toString());
                stmt.setString(6, longi.getText());
                stmt.setString(7, lati.getText());
                stmt.executeUpdate();

                stmt.close();
                conn.close();

            } catch (SQLException e1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText(e1.toString());
                alert.showAndWait();
            }

        } catch (SQLException e) {

        }
    }

    @FXML
    private void cancelClicked() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String sql2 = "SELECT COUNT(*) FROM source_report";
            PreparedStatement stmt2 = null;
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();

            stmt2 = conn.prepareStatement(sql2);
            ResultSet rs = stmt2.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getInt(1));
                count = 1 + rs.getInt(1);
            }
            stmt2.close();
        } catch (SQLException e1) {
            System.out.println(e1.toString());
        }
        waterCondition.getItems().setAll("Waste", "Treatable-Clear", "Treatable-Muddy", "Portable");
        waterType.getItems().setAll("Bottle", "Well", "Stream", "Lake", "Spring", "Other");
        dateAndTime.setText(currentDateandTime);
        reportNumber.setText("Report Number: " + count);
    }
}
