package booking.timer.utils;

import booking.timer.ui.PasswordModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.net.URL;

public class BookingTimerUtils {

    private static final int PASSWORD_LENGTH = 8;

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

    public static void generatePasswords(Integer howMany) {

        for (int i = 0; i < howMany; i++) {
            String password = RandomStringUtils.randomAlphanumeric(PASSWORD_LENGTH);
            PasswordModel.hashSet.add(password);
        }
    }

}
