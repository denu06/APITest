package com.softweb.util;


import java.time.LocalDate;
import java.util.Date;

public class generateRandomDate {

    public static void main(String[] args) {



            LocalDate randomDate = createRandomDate(2019, 2019);
            System.out.println(randomDate);

    }

    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(int startYear, int endYear) {
        Date date = new Date();
        Integer currentDate = date.getDate();
        Integer currentMonth =  date.getMonth();
        int day = createRandomIntBetween(1, currentDate);
        int month = createRandomIntBetween(1, currentMonth);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}
