package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by joon1 on 2017-04-21.
 */
public class WelcomeScreen implements Initializable {
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private void loginButtonAction(ActionEvent event) throws Exception {
        String databaseEmail = "";
        String databasePassword = "";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            System.err.print("ClassNotFoundException: ");
        }

        try {
            String sql = "SELECT * From user WHERE emailAddress = '" + email.getText() + "'";
            ConnectDB db = new ConnectDB();
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                databaseEmail = rs.getString("emailAddress");
                databasePassword = rs.getString("password");
            }
            rs.close();
            stmt.close();
            conn.close();

            if (email.getText().isEmpty() || password.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Information Alert");
                String s ="email or password cannot be empty";
                alert.setContentText(s);
                alert.show();
            } else {
                if (email.getText().equals(databaseEmail) && password.getText().equals(databasePassword)) {
                    Parent parent = FXMLLoader.load(getClass().getResource("/Layout/UserPage.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle("User Page");
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Information Alert");
                    String s ="email or password doesn't match";
                    alert.setContentText(s);
                    alert.show();
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        }
    }

//    private boolean attemptLogin(String email, String pass) {
//
//    }

    @FXML
    private void RegisterClicked(MouseEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/Layout/RegistrationPage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
