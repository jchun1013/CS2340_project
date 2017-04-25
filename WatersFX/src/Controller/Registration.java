package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Controller.WelcomeScreen.stage;

/**
 * Created by joon1 on 2017-04-21.
 */
public class Registration implements Initializable {
    @FXML
    private TextField fx_email;
    @FXML
    private TextField fx_password;
    @FXML
    private TextField fx_userName;
    @FXML
    private TextField fx_homeAddress;
    @FXML
    private TextField fx_name;
    @FXML
    private ComboBox<String> fx_userType;

    @FXML
    private void registerClicked(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        registerToDatabase();
        Parent parent = FXMLLoader.load(getClass().getResource("/Layout/WelcomePage.fxml"));
        stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Welcome Page");
        stage.show();
    }

    private void registerToDatabase() {
        try {
            PreparedStatement stmt = null;
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();

            if (fx_email.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty TextField");
                alert.setContentText("Please fill in email text field");
                alert.showAndWait();
            }

            if (fx_password.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty TextField");
                alert.setContentText("Please fill in password text field");
                alert.showAndWait();
            }

            if (fx_homeAddress.getText().isEmpty() || fx_name.getText().isEmpty() || fx_userName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty TextField");
                alert.setContentText("Please fill in every text field");
                alert.showAndWait();
            }

            String sql = "INSERT INTO user(`emailAddress`, `password`, `name`, `username`, `userType`, `address`, `isReporting`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";
            try {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, fx_email.getText());
                stmt.setString(2, fx_password.getText());
                stmt.setString(3, fx_name.getText());
                stmt.setString(4, fx_userName.getText());
                stmt.setString(5, fx_userType.getSelectionModel().getSelectedItem().toString());
                stmt.setString(6, fx_homeAddress.getText());
                stmt.setBoolean(7, false);
                stmt.executeUpdate();

                stmt.close();
                conn.close();
            } catch (SQLException e1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Email already exists");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error2");
            alert.setHeaderText("Error2");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelClicked(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/Layout/WelcomePage.fxml"));
        stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Welcome Page");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fx_userType.getItems().setAll("USER", "MANAGER", "WORKER", "ADMIN");
    }
}
