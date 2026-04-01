package org.Password_Generator;

/**
 * Evaluates a given password's strength.
 * Uses the <b>Password Entropy Formula</b> to calculate the password's Strength.
 * <br><br>
 */
public class StrengthChecker {
    /**
     * Computes and interprets the generated password's strength.
     * Using the <b>Password Entropy Formula</b> or <b>(passwordLength)*(Log2(charPoolSize))</b>.
     * <br><br>
     * It calculates the total entropy of the generated password in bits
     * and evaluates the bitSize with different thresholds where it should at least be <b>72</b> bits to be considered a viable password.
     * <br><br>
     * After calculating the entropy of the generated password in bits. Now, using <b>Normalization Formula,</b>
     * or <b>(initialValue - lowest possible entropy value)/(highest possible entropy value - lowest possible entropy value)</b>
     * it is then translated into a <b>Normal Value</b> where it is rated from 0 to 1
     *
     *
     * @param config if the password were to have a digit, special character, or a capital letter.
     * @param passwordLength the length of the password
     * @return the entropy of the password generated
     */
    public static double getStrength(Configurator config, int passwordLength) {
        int charPoolSize = 0;

        final int DIGIT_POOL_SIZE = 10;
        final int SPECIAL_CHAR_POOL_SIZE = 32;
        final int MIXED_CASE_POOL_SIZE = 52;
        final int UPPER_CASE_POOL_SIZE = 26;
        final int LOWER_CASE_POOL_SIZE = 26;


        if (config.hasDigit()) charPoolSize += DIGIT_POOL_SIZE;
        if (config.hasSpecialChar()) charPoolSize += SPECIAL_CHAR_POOL_SIZE;
        if (config.hasMixedCase()) charPoolSize += MIXED_CASE_POOL_SIZE;
        else {
            if (config.hasUpperCase()) charPoolSize += UPPER_CASE_POOL_SIZE;
            if (config.hasLowerCase()) charPoolSize += LOWER_CASE_POOL_SIZE;
        }

        return passwordLength * log2(charPoolSize);
    }

    /**
     * Interprets the <b>Descriptive Strength</b> of the password based on the thresholds.
     * @param initialEntropy the numerical initialEntropy of the password from 0 to 1
     * @return a descriptive interpretation of the initialEntropy of the password generated based on the threshold
     */
    public static String checkGeneratedStrength(double initialEntropy) {
        final double VERY_STRONG_THRESHOLD = 0.75;
        final double STRONG_THRESHOLD = 0.50;
        final double MEDIUM_THRESHOLD = 0.25;

        final double MIN_ENTROPY = 37.60351774512874;
        final double MAX_ENTROPY = 419.4936865073688;

        double normalizedStrength = (initialEntropy - MIN_ENTROPY)/(MAX_ENTROPY - MIN_ENTROPY);

        if (normalizedStrength >= VERY_STRONG_THRESHOLD) return "Very Strong";
        else if (normalizedStrength >= STRONG_THRESHOLD) return "Strong";
        else if (normalizedStrength >= MEDIUM_THRESHOLD) return "Medium";
        else return "Weak";
    }

    public static double checkStrength(double entropyBits) {
        if (entropyBits >= 128) return (double) 100 /100;
        else if (entropyBits >= 60) return (double) 75/100;
        else if (entropyBits >= 36) return (double) 50/100;
        else if (entropyBits >= 28) return (double) 25/100;
        else return (double) 0/100;
    }

    /**
     * Calculates the base-2 logarithm for a positive integer.
     * Returns 0 for an input of 1. Undefined for n <= 0.
     */
    private static double log2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        return Math.log(n) / Math.log(2);
    }
}
