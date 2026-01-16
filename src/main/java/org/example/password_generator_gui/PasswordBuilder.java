package org.example.password_generator_gui;

public class PasswordBuilder {
    private static final String lowerCases = "abcdefghijklmnopqrstuvwxyz"; //String containing all possible lowercase characters
    private static final String upperCases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //String containing all possible uppercase characters
    private static final String numericalChars = "0123456789"; //String containing all possible numerical values
    private static final String specialChars = "!@#$%^&*:';_-"; //String containing commonly used special characters

    public String passwordGenerator(int digit, boolean hasLowerCase, boolean hasNumbers, boolean hasSpecialChars, boolean hasUpperCase) {
        PasswordValidator passwordValidator = new PasswordValidator();

        StringBuilder charPoolBuilder = new StringBuilder();
        if (hasLowerCase) charPoolBuilder.append(lowerCases);
        if (hasUpperCase) charPoolBuilder.append(upperCases);
        if (hasNumbers) charPoolBuilder.append(numericalChars);
        if (hasSpecialChars) charPoolBuilder.append(specialChars);

        String charPoolString = charPoolBuilder.toString();
        String password = "";
        boolean validPassword = false;

        while (!validPassword) {
            StringBuilder passwordBuilder = new StringBuilder();
            for (int i = 0; i < digit; i++) {
                int rand = (int) (charPoolString.length() * Math.random());
                passwordBuilder.append(charPoolString.charAt(rand));
            }

            password = passwordBuilder.toString();

            if (passwordValidator.isValidPassword(password, hasLowerCase, hasNumbers, hasSpecialChars, hasUpperCase)) {
                validPassword = true;
            }
        }
        return password;
    }
}