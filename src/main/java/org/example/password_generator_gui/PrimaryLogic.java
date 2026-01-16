package org.example.password_generator_gui;

import java.util.Scanner;

public class PrimaryLogic {
        public String generatePassword() {
                Scanner scanner = new Scanner(System.in);
                PasswordBuilder passwordBuilder = new PasswordBuilder();
                System.out.print("Enter your desired password length>> ");
                int digit = scanner.nextInt();

                System.out.print("Will your desired password include lowercase characters?>> ");
                boolean hasLowerCase = scanner.nextBoolean();

                System.out.print("Will your desired password include uppercase characters?>> ");
                boolean hasUpperCase = scanner.nextBoolean();

                System.out.print("Will your desired password include numerical characters?>> ");
                boolean hasNumbers = scanner.nextBoolean();

                System.out.print("Will your desired password include special characters?>> ");
                boolean hasSpecialChars = scanner.nextBoolean();
                scanner.close();
                return passwordBuilder.passwordGenerator(digit, hasLowerCase, hasNumbers, hasSpecialChars, hasUpperCase);
        }
}