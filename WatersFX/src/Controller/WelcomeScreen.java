package Controller;

import Model.User;
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
import javafx.scene.Node;

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

    public static User currentUser;

    @FXML
    private void loginButtonAction(ActionEvent event) throws Exception {
        String databaseEmail = "";
        String databasePassword = "";
        String databaseName = "";
        String databaseUsername = "";
        String databaseUserType = "";
        String databaseAddress = "";
        boolean databaseIsReporting = false;

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
                databaseName = rs.getString("name");
                databaseUsername = rs.getString("username");
                databaseUserType = rs.getString("userType");
                databaseAddress = rs.getString("address");
                databaseIsReporting = rs.getBoolean("isReporting");
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
                    currentUser = new User(databaseUsername, databaseName, databaseEmail, databasePassword, databaseAddress, databaseUserType);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
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

    @FXML
    private void RegisterClicked(MouseEvent event) throws Exception {
        ((Node)(event.getSource())).getScene().getWindow().hide();
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
