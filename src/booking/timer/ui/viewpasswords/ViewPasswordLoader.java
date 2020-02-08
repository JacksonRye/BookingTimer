package booking.timer.ui.viewpasswords;

import booking.timer.utils.BookingTimerUtils;
import javafx.application.Application;
import javafx.stage.Stage;

public class ViewPasswordLoader extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BookingTimerUtils.loadWindow(getClass().getResource("view_passwords.fxml"), "Passwords", null);
    }
}
