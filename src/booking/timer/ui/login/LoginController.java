package booking.timer.ui.login;

import booking.timer.utils.Operation;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    Operation operation;

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private void login(ActionEvent event) {
        if (txtUsername.getText().equals("test") || txtPassword.getText().equals("test")) {
            System.out.println("Logged In");

            switch (operation) {
                case CLOSE:
                    Platform.exit();

            }

        }
    }

    public void setUp(Operation op) {
        operation = op;

    }
}
