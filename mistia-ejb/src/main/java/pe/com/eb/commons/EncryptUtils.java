package pe.com.eb.commons;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Formatter;

public class EncryptUtils {

	public static String sha256(String text) {

		String str = null;
		try {

			MessageDigest digest = null;
			digest = MessageDigest.getInstance("SHA-1");
			byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
			// str = Base64.getEncoder().encodeToString(hash);
			str =  byteToHex(hash);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return str;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

}
