package Controller;
/**
 * Created by joon1 on 2017-04-21.
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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
    private void ViewQRClicked() {

    }

    @FXML
    private void submitQRClicked() {

    }

    @FXML
    private void mapClicked() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Layout/Map.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
