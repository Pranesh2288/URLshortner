package com.pranesh.URLshortner.util;

public class Base62 {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = CHARACTERS.length();

    public static String encode(long input) {
        StringBuilder encodedString = new StringBuilder();
        if (input == 0) return String.valueOf(CHARACTERS.charAt(0));
        while (input > 0) {
            encodedString.append(CHARACTERS.charAt((int) (input % BASE)));
            input /= BASE;
        }
        return encodedString.reverse().toString();
    }
}