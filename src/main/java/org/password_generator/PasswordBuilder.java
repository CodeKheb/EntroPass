package org.password_generator;

import org.passay.PasswordGenerator;

public class PasswordBuilder {
    PasswordConfiguration config;
    PasswordGenerator generator;
    int passwordLength;

    public PasswordBuilder(int passwordLength, PasswordConfiguration config) {
        this.config = config;
        this.passwordLength = passwordLength;
        this.generator = new PasswordGenerator();
    }

    public String passwordBuilder(int passwordLength, PasswordConfiguration config) {
        return generator.generatePassword(passwordLength, config.getRules());
    }
}
