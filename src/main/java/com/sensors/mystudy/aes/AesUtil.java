package com.sensors.mystudy.aes;

import lombok.experimental.UtilityClass;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES工具类，参考网易提供的代码demo
 * @author chenyi@sensorsdata.cn
 * @date 2023/5/30 12:00
 */
@UtilityClass
public class AesUtil {
  /**
   * 安全随机数生成方式
   */
  private final static String SECURE_ALGORITHM = "SHA1PRNG";
  /**
   * 密钥算法
   */
  private static final String ALGORITHM = "AES";
  /**
   * 加密/解密算法-工作模式-填充模式
   */
  private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

  /**
   * 加密字符串
   * @param data 字符串
   * @param password 秘钥
   * @return 经加密-Base64编码后的字符串
   * @throws Exception
   */
  public static String encrypt(String data, String password) throws Exception { // NOSONAR
    return new String(Base64.getEncoder().encode(encryptByAesKey(data.getBytes(StandardCharsets.UTF_8), password)), StandardCharsets.UTF_8);

  }

  /**
   * 解密字符串
   * @param data 经加密-Base64编码后的字符串
   * @param password 秘钥
   * @return 解密后的字符串
   * @throws Exception
   */
  public static String decrypt(String data, String password) throws Exception { // NOSONAR
    return new String(decryptByAesKey(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8)), password), StandardCharsets.UTF_8);
  }

  private static Key generateKey(String password) throws NoSuchAlgorithmException { // NOSONAR
    KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
    SecureRandom secureRandom = SecureRandom.getInstance(SECURE_ALGORITHM);
    secureRandom.setSeed(password.getBytes());
    kgen.init(password.getBytes().length * 8, secureRandom);
    SecretKey secretKey = kgen.generateKey();
    return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
  }

  private static byte[] encryptByAesKey(byte[] data, String password) throws IllegalBlockSizeException,
      BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
    Key key = generateKey(password);
    Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM); // NOSONAR
    cipher.init(Cipher.ENCRYPT_MODE, key);
    return cipher.doFinal(data);
  }

  private static byte[] decryptByAesKey(byte[] encryptedData, String password)
      throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
      NoSuchPaddingException, InvalidKeyException {
    Key key = generateKey(password);
    Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM); // NOSONAR
    cipher.init(Cipher.DECRYPT_MODE, key);
    return cipher.doFinal(encryptedData);
  }


}
