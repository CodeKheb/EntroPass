package org.Password_Generator;

/**
 * Class that evaluates a given password's strength.
 * Uses the <b>Password Entropy Formula</b> to calculate the password's Strength.
 * <br><br>
 */
public class StrengthChecker {
    private final static int DIGIT_POOL_SIZE = 10;
    private final static int SPECIAL_CHAR_POOL_SIZE = 32;
    private final static int MIXED_CASE_POOL_SIZE = 52;
    private final static int LOWER_CASE_POOL_SIZE = 26;

    private final static double VERY_STRONG_THRESHOLD = 0.75;
    private final static double STRONG_THRESHOLD = 0.50;
    private final static double MEDIUM_THRESHOLD = 0.25;

    private final static double MIN_ENTROPY = 37.60351774512874;
    private final static double MAX_ENTROPY = 419.4936865073688;

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
     * @return a <b>Normalized</b> value of the strength of the password from 0 to 1
     */
    public static double getStrength(Configurator config, int passwordLength) {
        int charPoolSize = 0;

        if (config.hasDigit()) charPoolSize += DIGIT_POOL_SIZE;
        if (config.hasSpecialChar()) charPoolSize += SPECIAL_CHAR_POOL_SIZE;
        if (config.hasMixedCase()) charPoolSize += MIXED_CASE_POOL_SIZE;
        else charPoolSize += LOWER_CASE_POOL_SIZE;

        double initialEntropy = passwordLength * log2(charPoolSize);
        return (initialEntropy - MIN_ENTROPY)/(MAX_ENTROPY - MIN_ENTROPY);
    }

    /**
     * Interprets the <b>Descriptive Strength</b> of the password based on the thresholds.
     * @param strength the numerical strength of the password from 0 to 1
     * @return a descriptive interpretation of the strength of the password based on the threshold
     */
    public static String checkStrength(double strength) {
        if (strength >= VERY_STRONG_THRESHOLD) return "Very Strong";
        else if (strength >= STRONG_THRESHOLD) return "Strong";
        else if (strength >= MEDIUM_THRESHOLD) return "Medium";
        else return "Weak";
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
