package com.sensors.mystudy.aes;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * aes-ba64工具类
 * @author chenyi@sensorsdata.cn
 * @date 2022/8/11 16:19
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AesBase64Util {
  private static final String MODE = "AES/GCM/PKCS5Padding";

  /**
   * 加密字符串
   * @param str 字符串
   * @param key 秘钥
   * @param iv 建议12或16位字符串
   * @return 经加密-Base64编码后的字符串
   */
  public static String encryptToBase64 (String str, String key, String iv) {
    try {
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance(MODE);
      byte[] ivBytes = iv.getBytes();
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, new GCMParameterSpec(ivBytes.length * 8, ivBytes));
      // 字符串编码使用UTF-8
      byte[] encrypted = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception e) {
      log.error("AES加密错误", e);
    }
    return null;
  }

  /**
   * 解密字符串
   * @param encryptedStr 经加密-Base64编码后的字符串
   * @param key 秘钥
   * @param iv 建议12或16位字符串
   * @return 解密后的字符串
   */
  public static String decryptBase64 (String encryptedStr, String key, String iv) {
    try {
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance(MODE);
      byte[] ivBytes = iv.getBytes();
      cipher.init(Cipher.DECRYPT_MODE, keySpec, new GCMParameterSpec(ivBytes.length * 8, ivBytes));
      byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedStr));
      // 字符串编码使用UTF-8
      return new String(decrypted, StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.error("AES解密错误", e);
    }
    return null;
  }
}
