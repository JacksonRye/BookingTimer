package booking.timer.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

public class BookingTimerUtils {

    static Preferences preferences = Preferences.getPreferences();

    private static final int PASSWORD_LENGTH = preferences.getPasswordLength();

    public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }

    public static HashSet<String> generatePasswords(Integer howMany) {
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < howMany; i++) {
            String password = RandomStringUtils.randomAlphanumeric(PASSWORD_LENGTH);
            hashSet.add(password);
        }
        return hashSet;
    }

}
