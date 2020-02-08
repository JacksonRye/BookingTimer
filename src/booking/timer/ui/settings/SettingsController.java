package booking.timer.ui.settings;

import booking.timer.utils.AlertMaker;
import booking.timer.utils.Preferences;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtConfirmPassword;
    @FXML
    private Spinner spinnerPasswordLength;
    @FXML
    private Spinner spinnerTimeDuration;

    @FXML
    private void handleSavePreferences(ActionEvent event) {
        Preferences preferences = Preferences.getPreferences();

        String usernameText = txtUsername.getText().trim();
        String passwordText = txtPassword.getText().trim();
        String confirmPasswordText = txtConfirmPassword.getText().trim();

        Integer passwordLength = (Integer) spinnerPasswordLength.getValueFactory().getValue();
        Integer timeDuration = (Integer) spinnerTimeDuration.getValueFactory().getValue();

        Boolean flag = (usernameText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty());

        if (flag) {
            AlertMaker.showErrorMessage("Error", "Please fill all fields");
            return;
        }

        if (!passwordText.equals(confirmPasswordText)) {
            AlertMaker.showErrorMessage("Error", "Passwords do not match");
            return;
        }

        preferences.setPassword(passwordText);
        preferences.setUsername(usernameText);
        preferences.setPasswordLength(passwordLength);
        preferences.setTime(timeDuration);

        Preferences.writePreferenceToFile(preferences);

        AlertMaker.showSimpleAlert("Success", "Settings updated successfully");

        ((Stage) txtUsername.getScene().getWindow()).close();


    }

    @FXML
    private void cancelPreferences(ActionEvent event) {
        ((Stage) txtUsername.getScene().getWindow()).close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        spinnerTimeDuration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));
        spinnerPasswordLength.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 10, 5));
    }
}
