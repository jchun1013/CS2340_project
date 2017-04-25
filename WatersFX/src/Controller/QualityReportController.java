package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
public class QualityReportController implements Initializable{
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    private final String currentDateandTime = sdf.format(new Date());
    private int count = 0;

    @FXML
    private Label reportNumber;
    @FXML
    private Label dateTime;
    @FXML
    private Label name;
    @FXML
    private TextField lati;
    @FXML
    private TextField longi;
    @FXML
    private ComboBox<String> conditionCombo;
    @FXML
    private TextField virusPPM;
    @FXML
    private TextField contaminPPM;

    @FXML
    private void submitClicked() {
        try {
            PreparedStatement stmt = null;
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();

            if (lati.getText().isEmpty() || longi.getText().isEmpty()
                    || virusPPM.getText().isEmpty() || contaminPPM.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty TextField");
                alert.setContentText("Please fill in all text fields");
                alert.showAndWait();
            }

            String sql = "INSERT INTO purity_report(`dateTime`, `reportNumber`, `name`, `latitude`, `longitude`, `condition`, `virusPPM`, `contaminantPPM`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dateTime.getText());
            stmt.setInt(2, count);
            stmt.setString(3, name.getText());
            stmt.setString(4, lati.getText());
            stmt.setString(5, longi.getText());
            stmt.setString(6, conditionCombo.getSelectionModel().getSelectedItem().toString());
            stmt.setString(7, virusPPM.getText());
            stmt.setString(8, contaminPPM.getText());
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String sql2 = "SELECT COUNT(*) FROM purity_report";
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

        conditionCombo.getItems().setAll("Safe", "Treatable", "Unsafe");
        reportNumber.setText("" + count);
        dateTime.setText(currentDateandTime);
        name.setText(currentUser.getName());
    }
}
