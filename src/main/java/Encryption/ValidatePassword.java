package Encryption;

import org.mindrot.jbcrypt.BCrypt;

public class ValidatePassword {
    public static boolean checkPassword (String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }
}
