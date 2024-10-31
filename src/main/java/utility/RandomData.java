package utility;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomData {

    private RandomData() {

    }

    static Faker usFaker = new Faker(new Locale("en-US"));

    public static String firstName() {
        return usFaker.name().firstName() + "abc";
    }

    public static String lastName() {
        return usFaker.name().lastName();
    }

    public static String jobTitle() {
        return usFaker.job().title();
    }

    public static String password() {
        return usFaker.internet().password() + "A" + "!" + "1";
    }

    public static int idNumber() {
        return usFaker.number().numberBetween(1, 9999);
    }

}
