package ru.netology.main;

import lombok.Value;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    static Faker faker = new Faker((new Locale("ru")));

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {
        String[] ArrayCitys = {"Киров", "Омск", "Смоленск", "Москва", "Сочи", "Воронеж", "Казань", "Уфа"};
        Random random = new Random();
        int a = random.nextInt(ArrayCitys.length);
        return ArrayCitys[a];
    }

    public static String generateName(String locale) {
        return faker.name().fullName();
    }

    public static String generatePhone(String locale) {
        return faker.phoneNumber().cellPhone();
    }

    public static class Registration {
        private Registration() {

        }

        public static UserInfo generateUser(String locale) {
            String city = generateCity(locale);
            String name = generateName(locale);
            String phone = generatePhone(locale);
            DataGenerator.UserInfo user = new UserInfo(city, name, phone);
            return user;
        }

    }

    @Value

    public static class UserInfo {
        String city;
        String name;
        String phone;

    }
}