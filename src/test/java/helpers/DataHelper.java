package helpers;

import com.github.javafaker.Faker;

public class DataHelper {

    public static Faker faker = new Faker();

    public static String getRandomEmailAddress() {
        return faker.internet().emailAddress();
    }

    public static String getRandomFirstAndLastName() {
        return faker.name().firstName() + " " +  faker.name().lastName();
    }

}

