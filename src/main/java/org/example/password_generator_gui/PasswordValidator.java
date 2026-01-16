package org.example.password_generator_gui;

public class  PasswordValidator {
    private boolean passwordValidator (String password, boolean hasLowerCase, boolean hasNumbers, boolean hasSpecialChars, boolean hasUpperCase) {
        boolean hasNumber = password.matches(".*[0-9].*"); //Regex check if password contains a number
        boolean hasLower = password.matches(".*[a-z].*"); //Regex check if password contains a lowercase letter
        boolean hasCapital = password.matches(".*[A-Z].*"); //Regex check if password contains a capital letter
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*:';_-].*"); //Regex check if password contains a special character

        if (hasLowerCase) {
            return hasLower;
        }
        if (hasNumbers) {
            return hasNumber;
        }
        if (hasUpperCase) {
            return hasCapital;
        }
        if (hasSpecialChars) {
            return hasSpecialChar;
        }
        return false;
    }
    //Getter Method
    public boolean isValidPassword(String password, boolean hasLowerCase, boolean hasUpperCase, boolean hasNumbers, boolean hasSpecialChars) {
        return passwordValidator(password, hasLowerCase, hasUpperCase, hasNumbers, hasSpecialChars);
    }
}
