package com.airlines.services;

public class UserService {
    private static final int SHIFT = 2; // Shift by 2 for Caesar Cipher

    // Encrypt password using Caesar Cipher
    public static String encryptPassword(String password) {
        StringBuilder encrypted = new StringBuilder();
        
        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) { 
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                encrypted.append((char) ((ch - base + SHIFT) % 26 + base));
            } else {
                encrypted.append(ch); // Keep numbers & special chars unchanged
            }
        }
        return encrypted.toString();
    }
}
