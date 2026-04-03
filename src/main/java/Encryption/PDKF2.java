package Encryption;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PDKF2 {
    public static SecretKey deriveKey(char[] masterPassword, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(
                masterPassword,
                salt,
                310_000,
                128 // key length
        );

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();

        spec.clearPassword(); //wipe the password from memory
        return new SecretKeySpec(keyBytes, "AES");
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        return salt;
    }

    public static String getSalt() {
        byte[] salt = generateSalt();
        return Base64.getEncoder().encodeToString(salt);
    }
}
