package booking.timer.ui.settings;

import booking.timer.utils.BookingTimerUtils;
import javafx.application.Application;
import javafx.stage.Stage;

public class SettingsLoader extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BookingTimerUtils.loadWindow(getClass().getResource("settings.fxml"),
                "Settings", primaryStage);

    }
}
