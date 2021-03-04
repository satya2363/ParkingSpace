package com.parkingspace.utils;

import java.util.Random;

public class RandomStringGenerator {
    String randomString;
    //int    randomNumber;

    public RandomStringGenerator() {
        randomString = "abcdefghijklmnopqrstuvwxyz";
    }

    public String getAlphaNumericCode(int length) {
        Random rand = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                str.append(rand.nextInt(10));
            } else {
                str.append(randomString.charAt(rand.nextInt(26)));
            }
        }
        return str.toString();
    }

    public int getNumericCode(int length) {
        return length;
    }
}
