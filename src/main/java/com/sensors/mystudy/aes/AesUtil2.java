package com.sensors.mystudy.aes;


import lombok.experimental.UtilityClass;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Aes工具类，参考创纪云提供的demo
 */
@UtilityClass
public class AesUtil2 {
	
	private static final String AES = "AES";
	private static final int DEFAULT_AES_KEY_SIZE = 128;
	private static final int DEFAULT_IV_SIZE = 16;
	/**
	 * CBC模式：
	 * 密码分组链接模式    Cipher Block Chaining
	 */
	private static final String AES_CBC = "AES/CBC/PKCS5Padding"; //NOSONAR
	private static final SecureRandom random = new SecureRandom();

    /**
	 * 生成AES密钥,返回字节数组, 默认长度为128位(16字节).
	 */
	public static String generateAesKeyString() throws NoSuchAlgorithmException {
		return encode(generateAesKey(DEFAULT_AES_KEY_SIZE));
	}
	
	/**
	 * 生成AES密钥,可选长度为128,192,256位.
	 */
	public static byte[] generateAesKey(int keySize) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
		keyGenerator.init(keySize);
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey.getEncoded();
	}
	
	/**
	 * 生成随机向量,默认大小为cipher.getBlockSize(), 16字节.
	 */
	public static String generateIVString(){
		return encode(generateIV());
	}

	/**
	 * 生成随机向量,默认大小为cipher.getBlockSize(), 16字节.
	 */
	public static byte[] generateIV() {
		byte[] bytes = new byte[DEFAULT_IV_SIZE];
		random.nextBytes(bytes);
		return bytes;
	}
	
	/**
	 * 使用AES加密原始字符串.
	 *
	 * @param input 原始输入字符串
	 * @param key 符合AES要求的密钥
	 * @param iv 向量字符串
	 * @return
	 */
	public static String encrypt(String input, String key, String iv) throws Exception{
		return encode(encrypt(input.getBytes(StandardCharsets.UTF_8), decode(key), decode(iv)));
	}
 
    /**
     * 使用AES解密字符串, 返回原始字符串.
     * 
     * @param input 加密字符串
     * @param key 符合AES要求的密钥
     * @param iv 向量字符串
     * @return
     */
		public static String decrypt(String input, String key, String iv) throws Exception{
			return new String(decrypt(decode(input), decode(key), decode(iv)), StandardCharsets.UTF_8);
	}
    
    /**
	 * 使用AES加密原始字符串.
	 * 
	 * @param input 原始输入字符数组
	 * @param key 符合AES要求的密钥
	 * @param iv 初始向量
	 */
	public static byte[] encrypt(byte[] input, byte[] key, byte[] iv) throws Exception {
		return aes(input, key, iv, Cipher.ENCRYPT_MODE);
	}
	
	/**
	 * 使用AES解密字符串, 返回原始字符串.
	 * 
	 * @param input Hex编码的加密字符串
	 * @param key 符合AES要求的密钥
	 * @param iv 初始向量
	 */
	public static byte[] decrypt(byte[] input, byte[] key, byte[] iv) throws Exception {
		return aes(input, key, iv, Cipher.DECRYPT_MODE);
	}

	private static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) throws Exception { // NOSONAR
		KeyGenerator generator = KeyGenerator.getInstance( "AES" );
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(key);
		generator.init(DEFAULT_AES_KEY_SIZE, secureRandom);
		SecretKey secretKey =generator.generateKey();
		IvParameterSpec ivSpec = new IvParameterSpec(iv); //NOSONAR
		Cipher cipher = Cipher.getInstance(AES_CBC);  //NOSONAR
		cipher.init(mode, secretKey, ivSpec);
		return cipher.doFinal(input);
	}

	/**
	 * 字节数组转string
	 * @param b
	 * @return
	 */
	private static String encode(byte[] b){
		return new String(Hex.encodeHex(b));
	}

	/**
	 * string转字节数组
	 * @param s
	 * @return
	 * @throws DecoderException
	 */
	private static byte[] decode(String s) throws DecoderException {
		return Hex.decodeHex(s.toCharArray());
	}
}
