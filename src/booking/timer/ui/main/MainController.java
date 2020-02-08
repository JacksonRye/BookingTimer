package booking.timer.ui.main;

import booking.timer.database.DataHelper;
import booking.timer.ui.login.LoginController;
import booking.timer.utils.BookingTimerUtils;
import booking.timer.utils.Operation;
import booking.timer.utils.Preferences;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    Timeline timeline;

    static Preferences preferences = Preferences.getPreferences();
    public static final Integer STARTTIME = preferences.getTime() * 60;
    Timeline timerTimeline;
    WebHistory history;

    @FXML
    private WebView webView;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    @FXML
    private Label timerLabel;


    public void setUp() {
        webView.getEngine().load("https://shop.bet9ja.com/Sport/Default.aspx");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUp();
        timerLabel.textProperty().bind(timeSeconds.asString());


        timeline = new Timeline(new KeyFrame(Duration.seconds(STARTTIME + 1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                timeline.stop();

                TextInputDialog textInputDialog = new TextInputDialog();
                textInputDialog.getDialogPane().getScene().getWindow().setOnCloseRequest((dialogEvent) -> dialogEvent.consume());
                final Button btnOk = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.OK);
                btnOk.addEventFilter(ActionEvent.ACTION,
                        (event1) -> {
                            if (!DataHelper.isPasswordValid(textInputDialog.getEditor().getText())) {
                                event1.consume();
                            }
                        });

                final Button btnCancel = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL);

                btnCancel.addEventFilter(ActionEvent.ACTION, (event1 -> event1.consume()));

                textInputDialog.show();
                textInputDialog.setOnHiding((e) -> timeline.play());
                timerTimeline.playFromStart();

            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

    }

    @FXML
    private void startTimer(ActionEvent event) {
        timeline.play();
        timeSeconds.set(STARTTIME);
        timerTimeline = new Timeline();
        timerTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(STARTTIME + 1),
                        new KeyValue(timeSeconds, 0))
        );
        timerTimeline.playFromStart();
    }


    @FXML
    private void handleClose(ActionEvent event) {
        LoginController controller = (LoginController) BookingTimerUtils.loadWindow(getClass().getResource("/booking/timer/ui/login/login.fxml"),
                "Login", null);
        controller.setUp(Operation.CLOSE);
    }

    @FXML
    private void handleViewPasswords(ActionEvent event) {
        LoginController controller = (LoginController) BookingTimerUtils.loadWindow(getClass().getResource("/booking/timer/ui/login/login.fxml"),
                "Login", null);
        controller.setUp(Operation.VIEW_PASSWORDS);
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        history = webView.getEngine().getHistory();
        webView.getEngine().reload();
    }

    @FXML
    private void goForward(ActionEvent event) {
        history = webView.getEngine().getHistory();
        try {
            history.go(1);
        } catch (IndexOutOfBoundsException e) {

        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        history = webView.getEngine().getHistory();
        try {
            history.go(-1);
        } catch (IndexOutOfBoundsException e) {

        }
    }

    @FXML
    private void loadSettings(ActionEvent event) {
        LoginController controller = (LoginController) BookingTimerUtils.loadWindow(getClass().getResource("/booking/timer/ui/login/login.fxml"),
                "Settings", null);
        controller.setUp(Operation.SETTINGS);
    }

    @FXML
    private void loadAbout(ActionEvent event) {
        BookingTimerUtils.loadWindow(getClass().getResource("/booking/timer/ui/about/About.fxml"),
                "About", null);
    }
}
