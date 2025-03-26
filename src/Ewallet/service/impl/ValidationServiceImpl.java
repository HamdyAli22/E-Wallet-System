package Ewallet.service.impl;

import Ewallet.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validateUsername(String username) {
        if (username.length() < 3) {
            System.out.println("Username must be at least 3 characters long.");
            return false;
        }
        if(!Character.isUpperCase(username.charAt(0))){
            System.out.println("Username must start with an uppercase letter.");
            return false;
        }
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long.");
            return false;
        }
        if(!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") ||
           !password.matches(".*\\d.*")   || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")){
            System.out.println("Password must contain at least one number, one uppercase letter, one lowercase letter, and one special character.");
            return false;
        }
        return true;
    }
}
