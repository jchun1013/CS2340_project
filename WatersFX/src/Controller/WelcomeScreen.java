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
        if (attemptLogin(email.getText(), password.getText())) {
            Parent parent = FXMLLoader.load(getClass().getResource("/Layout/UserPage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("User Page");
            stage.show();
        } else {
            
        }
    }

    private boolean attemptLogin(String email, String pass) {

    }

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
