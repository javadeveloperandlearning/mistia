package pe.com.eb.commons;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES {


    private static Cipher _ecipher;
    private static Cipher _dcipher;
    private static String semilla = "ABCDEABCDEABCDEABCDE";


    public static String encrypt(final String string) {
        try {
        	
        	
           	final MessageDigest md = MessageDigest.getInstance("md5");
        	final byte[] digestOfPassword = md.digest(semilla.getBytes("utf-8"));
        	final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        	final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        	final IvParameterSpec params = new IvParameterSpec(new byte[8]);
        	
            _ecipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        	_ecipher.init(Cipher.ENCRYPT_MODE, key, params);
        	
            // Encode the string into bytes using utf-8
            final byte[] bytes = string.getBytes("UTF-8");

            // Encrypt
            final byte[] enc = _ecipher.doFinal(bytes);

            // Encode bytes to base64 to get a string
            return bytesToHex(enc);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(final String str) {
        try {
           	final MessageDigest md = MessageDigest.getInstance("md5");
        	final byte[] digestOfPassword = md.digest(semilla.getBytes("utf-8"));
        	final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        	final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        	final IvParameterSpec params = new IvParameterSpec(new byte[8]);
        	
            _dcipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            _dcipher.init(Cipher.DECRYPT_MODE, key, params);
        	
            // Decode base64 to get bytes
            final byte[] dec = hexToBytes(str);

            // Decrypt
            final byte[] utf8 = _dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (final Exception e) {
            return null;
        }
    }

    private static String bytesToHex(final byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (final byte b : bytes) {
            final String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                buf.append("0");
            }
            buf.append(hex);
        }
        return buf.toString();
    }

    private static byte[] hexToBytes(final String hex) {
        final byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }
}
