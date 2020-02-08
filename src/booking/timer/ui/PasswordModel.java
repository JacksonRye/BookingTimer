package booking.timer.ui;

import booking.timer.utils.BookingTimerUtils;

import java.util.HashSet;

public class PasswordModel {

    public static HashSet<String> hashSet = new HashSet<>();

    {
    }

    public static void main(String[] args) {
        BookingTimerUtils.generatePasswords(8);
        for (String password : hashSet) System.out.println(password);
    }

}
