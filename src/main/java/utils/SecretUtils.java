package utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class SecretUtils {

    private static final int KEY_SIZE = 256;
    private static final String ALGORITHM = "AES";
    private static final String HMAC_256 = "HmacSHA256";

    private SecretUtils() {
    }

    public static String getSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(new SecureRandom());
            keyGen.init(KEY_SIZE);
            return Hex.encodeHexString(keyGen.generateKey().getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to create secret key", e);
        }
    }

    public static String getHmac(String secretKey, String message) {
        byte[] hmacSha256 = null;
        try {
            Mac mac = Mac.getInstance(HMAC_256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_256);
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(message.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return Hex.encodeHexString(hmacSha256);
    }
}
