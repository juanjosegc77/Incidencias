package Crypt;

/**
 * @author Ing. Juan José Guzmán Cruz
 */

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {
    
    private static final String algorithm = "AES";
    private static final String ciper_algorithm = "AES/CBC/PKCS5Padding";
    private static final String iv = "-vUrm#uq8r?wñEx";
    private static final String key = "kbr%dudle¿tl7i2";

    public final String encrypt(String value) {

        String encryptedVal = null;
        try {
            final SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
            final Cipher cipher = Cipher.getInstance(ciper_algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secret_key, new IvParameterSpec(iv.getBytes("UTF-8")));
            byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            //encryptedVal = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);
            encryptedVal = Base64.getEncoder().encodeToString(encryptedByteValue);
            //encryptedVal = Base64.encodeToString(encryptedByteValue, 0);
        } catch(Exception ex) {
            System.out.println("Desde Crypto.encrypt: La excepción es = " + ex);
        }

        return encryptedVal;
    }

    public final String decrypt(String value) {

        String decryptedValue = null;

        try {
            final SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
            final Cipher cipher = Cipher.getInstance(ciper_algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secret_key, new IvParameterSpec(iv.getBytes("UTF-8")));
            //byte[] decryptedValue64 = Base64.decode(value, Base64.DEFAULT);
            byte[] decryptedValue64 = Base64.getDecoder().decode(value);
            byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
            decryptedValue = new String(decryptedByteValue,"utf-8");
        } catch(Exception ex) {
            System.out.println("Desde Crypto.decrypt : La excepción es = " + ex);
        }
        return decryptedValue;
    }
}


