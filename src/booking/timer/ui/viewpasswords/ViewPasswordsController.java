package booking.timer.ui.viewpasswords;

import booking.timer.database.DataHelper;
import booking.timer.utils.BookingTimerUtils;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewPasswordsController implements Initializable {

    ObservableList<String> passwordList = FXCollections.observableArrayList();

    @FXML
    private JFXListView<String> listView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataHelper.setPasswordsToView(passwordList);
        listView.setItems(passwordList);
    }

    @FXML
    private void generatePasswords(ActionEvent event) {
        new Thread(() -> {
            DataHelper.addPasswords(BookingTimerUtils.generatePasswords(100));
            DataHelper.setPasswordsToView(passwordList);
            listView.setItems(passwordList);
        }).start();

    }
}
