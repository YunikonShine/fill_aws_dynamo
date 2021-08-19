package br.com.yunikonshine.fillawsdynamo.utils;

import br.com.yunikonshine.fillawsdynamo.model.BaseEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    private static Random random = new Random();

    public static String generateRandomString(Integer size, Integer wordsNumber) {
        return generateRandomString(size, wordsNumber, " ");
    }

    public static String generateRandomString(Integer size, Integer wordsNumber, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < wordsNumber; i++) {
            stringBuilder.append(separator);
            stringBuilder.append(random.ints(65, 123)
                    .filter(number -> (number <= 90 || number >= 97))
                    .limit(size)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString());
        }
        return stringBuilder.substring(1, stringBuilder.length());
    }

    public static String generateRandomStringNumber(Integer size) {
        return random.ints(48, 58)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static Integer generateRandomInteger(Integer min, Integer max) {
        return random.ints(min, max).findFirst().getAsInt();
    }

    public static Double generateRandomDouble(Double min, Double max, Integer decimalPlaces) {
        decimalPlaces = (int) Math.pow(10, decimalPlaces);
        return Math.floor(random.doubles(min, max).findFirst().getAsDouble() * decimalPlaces) / decimalPlaces;
    }

    public static String generateHash() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomDateString(String pattern) {
        return generateRandomDate().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate generateRandomDate() {
        long startEpochDay = LocalDate.now().minusYears(generateRandomInteger(1, 10)).toEpochDay();
        long endEpochDay = LocalDate.now().plusYears(generateRandomInteger(1, 10)).toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static String generateRandomStatus(BaseEnum[] baseEnum) {
        Integer code = generateRandomInteger(0, baseEnum.length);
        return baseEnum[code].toString();
    }

    public static LocalDateTime generateRandomDateTime() {
        return generateRandomDate().atTime(
                generateRandomInteger(0, 24),
                generateRandomInteger(0, 60),
                generateRandomInteger(0, 60),
                generateRandomInteger(0, 999999));
    }

    public static String generateRandomDateTimeString(String pattern) {
        return generateRandomDateTime().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static Boolean generateRandomBoolean() {
        return random.nextBoolean();
    }

}
