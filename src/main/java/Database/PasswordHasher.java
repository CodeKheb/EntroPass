package Database;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    public static String hashPassword(String Password) {
         return BCrypt.hashpw(Password, BCrypt.gensalt(12));
    }
}
