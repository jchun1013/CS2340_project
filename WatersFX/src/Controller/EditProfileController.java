package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Controller.WelcomeScreen.currentUser;

/**
 * Created by joon1 on 2017-04-25.
 */
public class EditProfileController implements Initializable {
    @FXML
    private TextField newUserID;
    @FXML
    private TextField newName;
    @FXML
    private TextField newAddress;
    @FXML
    private void editClicked() {
        try {
            ConnectDB db = new ConnectDB();
            Connection conn = db.getConnection();
            if (!newUserID.getText().isEmpty()) {
                String sql1 = "UPDATE user SET username = '" + newUserID.getText() + "'" +
                        "WHERE emailAddress = '" + currentUser.getEmailAddress() + "'";
                PreparedStatement stmt = conn.prepareStatement(sql1);
                stmt.executeUpdate();
            }
            if (!newName.getText().isEmpty()) {
                String sql2 = "UPDATE user SET name = '" + newName.getText() + "'" +
                        "WHERE emailAddress = '" + currentUser.getEmailAddress() + "'";
                PreparedStatement stmt = conn.prepareStatement(sql2);
                stmt.executeUpdate();
            }
            if (!newAddress.getText().isEmpty()) {
                String sql3 = "UPDATE user SET address = '" + newAddress.getText() + "'" +
                        "WHERE emailAddress = '" + currentUser.getEmailAddress() + "'";
                PreparedStatement stmt = conn.prepareStatement(sql3);
                stmt.executeUpdate();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit Successful");
            alert.setHeaderText("Edit Profile");
            alert.setContentText("Successfully edited!");
            alert.showAndWait();



        } catch (SQLException e) {

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
