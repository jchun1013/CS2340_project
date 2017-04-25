package Controller;
/**
 * Created by joon1 on 2017-04-21.
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static Controller.WelcomeScreen.currentUser;

public class UserPageController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane rootPane;

    @FXML
    private void viewSRClicked() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Layout/viewSource.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

    @FXML
    private void submitSRClicked() throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Layout/SourceReport.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

    @FXML
    private void ViewQRClicked() throws Exception {
        if (currentUser.getUsertype().equals("MANAGER")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/Layout/viewQuality.fxml"));
            anchorPane.getChildren().setAll(pane);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Restriction");
            alert.setHeaderText("Restriction");
            alert.setContentText("Only manager has access this function");
            alert.showAndWait();
        }
    }

    @FXML
    private void submitQRClicked() throws Exception {
        if (currentUser.getUsertype().equals("MANAGER") || currentUser.getUsertype().equals("WORKER")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/Layout/QualityReport.fxml"));
            anchorPane.getChildren().setAll(pane);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Restriction");
            alert.setHeaderText("Restriction");
            alert.setContentText("Only manager or worker can access this function");
            alert.showAndWait();
        }
    }

    @FXML
    private void mapClicked() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Layout/Map.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

    @FXML
    private void editProfileClicked() throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Layout/EditProfile.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
