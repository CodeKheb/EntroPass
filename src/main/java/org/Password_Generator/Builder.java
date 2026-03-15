package org.Password_Generator;

import org.passay.PasswordGenerator;

/**
 * Class specifically used to generate a password based on the specific configurations and requirements
 * the user has entered.
 * <br><br>
 * Uses the {@code Passay Library} to generate a highly randomized password
 */
public class Builder {
    private final PasswordGenerator generator;
    private final int passwordLength;

    /**
     * Constructor that creates a password generation object based on the length of the password.
     * <br><br>
     * Additionally, this object enforces a strict rule in which
     * the password's length must be <b>at least 8 characters long and 64 characters long at most</b>.
     * @param passwordLength the desired password length
     */
    public Builder(int passwordLength) {
        this.passwordLength = passwordLength;
        this.generator = new PasswordGenerator();

        if (passwordLength < 8 || passwordLength > 64) throw new IllegalArgumentException("Password length must be between 8 and 64 characters!");
    }

    /**
     * This method is the password generator method itself
     * @param config the requirements in the password such as the required inclusion of:
     *               <li>
     *                  <ul>Uppercase Letters</ul>
     *                  <ul>Numbers</ul>
     *                  <ul>Special characters (specifically the special characters included in ascii)</ul>
     *               </li>
     * @return a strongly randomized password
     */
    public String buildPassword(Configurator config) {
        return generator.generatePassword(this.passwordLength, config.getRules());
    }
}
