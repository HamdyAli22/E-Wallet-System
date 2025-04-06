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
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}:;\"'|,.<>/?]).*$")) {
            System.out.println("Password must contain at least one number, one uppercase letter, one lowercase letter, and one special character.");
            return false;
        }
        return true;
    }
    public boolean validateAmount(String amount) {
        try{
            if(Double.parseDouble(amount) == 0){
                System.out.println("Amount must be greater than zero.");
                return false;
            }
            return true;
        }catch(NumberFormatException e){
            System.out.println("Amount must be a number.");
            return false;
        }

    }
}
