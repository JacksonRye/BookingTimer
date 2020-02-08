package booking.timer.ui.main;

import booking.timer.ui.login.LoginController;
import booking.timer.utils.BookingTimerUtils;
import booking.timer.utils.Operation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    Timeline timeline;

    @FXML
    private WebView webView;

    public void setUp() {
        webView.getEngine().load("https://shop.bet9ja.com/Sport/Default.aspx");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUp();
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                timeline.stop();

                TextInputDialog textInputDialog = new TextInputDialog();
                final Button btnOk = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.OK);
                btnOk.addEventFilter(ActionEvent.ACTION,
                        (event1) -> {
                            if (!passwordValid(textInputDialog.getEditor().getText())) {
                                event1.consume();
                            }
                        });

                final Button btnCancel = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL);

                btnCancel.addEventFilter(ActionEvent.ACTION, (event1 -> event1.consume()));

                textInputDialog.show();
                textInputDialog.setOnHiding((e) -> timeline.play());

            }

            private boolean passwordValid(String text) {
                return text.equals("cherry");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

    }

    @FXML
    private void startTimer(ActionEvent event) {
        timeline.play();
    }


    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Time uo");
        alert.showAndWait();
    }

    @FXML
    private void handleClose(ActionEvent event) {
        LoginController controller = (LoginController) BookingTimerUtils.loadWindow(getClass().getResource("/booking/timer/ui/login/login.fxml"),
                "Login", null);
        controller.setUp(Operation.CLOSE);

    }
}
