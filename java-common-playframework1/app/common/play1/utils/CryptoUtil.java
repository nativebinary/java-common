package common.play1.utils;

import common.basic.utils.StringUtil;
import common.play1.externals.BCrypt;
import play.libs.Crypto;

public class CryptoUtil {
    public static final String salt = "$2a$10$.X6RtBHecW9dKTXg5Njk36";//"$2a$10$.X6RtBHecW9dKTXg5Njk3u";

    public static String generateSalt(){
		return BCrypt.gensalt();
	}
	
	public static String encrypt(String salt, String plainText) {
		return Crypto.passwordHash(BCrypt.hashpw(plainText, salt), Crypto.HashType.SHA512);
	}

	public static boolean isValid(String salt, String plainText, String cipherText) {
        return StringUtil.equals(cipherText, encrypt(salt, plainText));
	}

    public static String encryptAES(String salt, String plainText) {
        return Crypto.encryptAES(plainText, salt.substring(0, 16));
    }
    public static String decryptAES(String salt, String encryptText) {
        try {
            return Crypto.decryptAES(encryptText, salt.substring(0, 16));
        } catch (Exception e) {
            return "";
        }
    }




}
