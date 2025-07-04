package jp.co.aforce.others;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher {

	// 設定値
	private static final int SALT_LENGTH = 16;
	private static final int ITERATIONS = 100_000;
	private static final int KEY_LENGTH = 256; // bits

	// ソルト生成
	public static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_LENGTH];
		random.nextBytes(salt);
		return salt;
	}
	
	// パスワードハッシュ生成
	public static String hashPassword(String password, byte[] salt) throws Exception {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		return Base64.getEncoder().encodeToString(hash);
	}

	// ハッシュ＋ソルトを返す（保存用など）
	public static String[] createHashedPassword(String password) throws Exception {
		byte[] salt = generateSalt();
		String hash = hashPassword(password, salt);
		String encodedSalt = Base64.getEncoder().encodeToString(salt);
		return new String[] { hash, encodedSalt };
	}

	// パスワード検証
	public static boolean verifyPassword(String password, String encodedSalt, String expectedHash) throws Exception {
		byte[] salt = Base64.getDecoder().decode(encodedSalt);
		String hash = hashPassword(password, salt);
		return hash.equals(expectedHash);
	}
}
