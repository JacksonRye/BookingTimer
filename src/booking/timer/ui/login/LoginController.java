package booking.timer.ui.login;

import booking.timer.utils.BookingTimerUtils;
import booking.timer.utils.Operation;
import booking.timer.utils.Preferences;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginController {

    Operation operation;

    static Preferences preferences = Preferences.getPreferences();

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private void login(ActionEvent event) {


        String usernameText = txtUsername.getText().trim();
        String passwordText = DigestUtils.sha1Hex(txtPassword.getText().trim());
        if (usernameText.equals(preferences.getUsername()) &&
                passwordText.equals(preferences.getPassword())) {

            System.out.println("Logged In");

            switch (operation) {
                case CLOSE:
                    closeLogin();
                    Platform.exit();
                    System.exit(0);
                    break;
                case VIEW_PASSWORDS:
                    BookingTimerUtils.loadWindow(getClass().getResource("/booking/timer/ui/viewpasswords/view_passwords.fxml"),
                            "View Passwords", null);
                    closeLogin();
                    break;
                case SETTINGS:
                    BookingTimerUtils.loadWindow(getClass().getResource("/booking/timer/ui/settings/settings.fxml"),
                            "Settings", null);
                    break;
                default:
                    break;

            }

        }
    }

    public void setUp(Operation op) {
        operation = op;

    }

    void closeLogin() {
        ((Stage) txtPassword.getScene().getWindow()).close();
    }

    @FXML
    private void cancel(ActionEvent event) {
        closeLogin();
    }
}
