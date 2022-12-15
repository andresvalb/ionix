package cl.ionix.test.backend.service.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * The Class Cifrar.
 */
public class Cifrar {

	/** The Constant UTF8. */
	private static final String UTF8 = "UTF8";

	/**
	 * Instantiates a new cifrar.
	 */
	private Cifrar() {
		// Constructor.
	}

	/**
	 * Rut cifrado.
	 *
	 * @param rut the rut
	 * @return the string
	 */
	public static String rutCifrado(String rut) {

		DESKeySpec keySpec;
		String encryptedValue = "";

		try {
			keySpec = new DESKeySpec("ionix123456".getBytes(UTF8));

			SecretKey secretKey = new SecretKeySpec(keySpec.getKey(), "DES");

			byte[] cleartext = rut.getBytes(UTF8);

			Cipher cipher = Cipher.getInstance("DES");

			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] encValue = cipher.doFinal(cleartext);

			encryptedValue = Base64.getEncoder().encodeToString(encValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptedValue;

	}

}
