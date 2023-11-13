package model.user;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class CredentialManager {
    /**
     *
     * @param username
     * @param password
     * @return
     */
    public static User loginWithUsernameAndPassword(String username, String password) {
        byte[] usernameByteArray = username.getBytes();
        byte[] salt = new byte[16];
        for (int i = 0; i < salt.length; i++) {
            salt[i] = usernameByteArray[i % usernameByteArray.length];
        }
        byte[] passwordHash = getPasswordHash(password, salt);
        for (int i = 0; i < passwordHash.length; i++) {
            System.out.print(passwordHash[i] + " ");
        }

        return null;
    }

    /**
     *
     * @param username
     * @param password
     */
    public static void registerWithUsernameAndPassword(String username, String password) {

    }

    public static byte[] getPasswordHash(String password, byte[] salt) {
        SecureRandom random = new SecureRandom();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt,
                65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            System.out.println("Error while hashing password!");
        }
        return null;
    }
}