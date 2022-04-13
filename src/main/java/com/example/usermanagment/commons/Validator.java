package com.example.usermanagment.commons;

import java.nio.CharBuffer;
import java.util.Optional;
import java.util.regex.Pattern;

public class Validator {

    private static final String PASSWORD = Constants.PASSWORD_PATTERN;

    private static final Pattern N_PASSWORD_PATTERN = Pattern.compile(PASSWORD);

    private Validator() {
    }

    public static boolean validatePassword(final char[] password) {

        if (password == null || password.length == 0) {
            return false;
        }
        try {
            return Optional.of(password)
                .map(pass -> N_PASSWORD_PATTERN.matcher(CharBuffer.wrap(password)).matches())
                .orElse(Boolean.FALSE);
        } catch (Exception ex) {
            return false;
        }

    }
}
