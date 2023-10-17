package core;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DESUtils {

    //密钥算法
    private static final String KEY_ALGORITHM = "DES";

    //加密算法/工作模式/填充方式
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    
    

    /**使用DES对字符串加密
     * @param str utf8编码的字符串
     * @param key 密钥（8字节）
     * @return 加密结果
     * @throws Exception
     */
    public static byte[] desEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), KEY_ALGORITHM));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return  bytes;
    }
    /**使用DES对数据解密
     * @param bytes utf8编码的二进制数据
     * @param key 密钥（8字节）
     * @return 解密结果
     * @throws Exception
     */
    public static String desDecrypt(byte[] bytes, String key) throws Exception {
        if (bytes == null || key == null) return null;
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), KEY_ALGORITHM));
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }

}


