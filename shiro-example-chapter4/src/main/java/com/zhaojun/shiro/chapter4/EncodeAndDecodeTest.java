package com.zhaojun.shiro.chapter4;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

import java.security.Key;

/**
 * @author zhaojun0193
 * @create 2018/4/25
 */
@Slf4j
public class EncodeAndDecodeTest {

    @Test
    public void base64encode() {
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());

        log.info("编码后：" + base64Encoded);

        String str2 = Base64.decodeToString(base64Encoded);

        log.info("解码后：" + str2);
    }

    @Test
    public void hexEncode() {
        String str = "hello";
        String base64Encoded = Hex.encodeToString(str.getBytes());
        log.info("编码后：" + base64Encoded);
        String str2 = new String(Hex.decode(base64Encoded.getBytes()));
        log.info("解码后：" + str2);
    }

    @Test
    public void md5() {
        String str = "hello";
        String salt = "123";

        String md5 = new Md5Hash(str, salt).toString();

        log.info(md5);
    }

    @Test
    public void sha256() {
        String str = "hello";
        String salt = "123";
        String sha256 = new Sha256Hash(str, salt).toString();
        log.info(sha256);
    }

    @Test
    public void messageDigest() {
        String str = "hello";
        String salt = "123";
        //内部使用MessageDigest
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        log.info(simpleHash);
    }

    @Test
    public void testHashService() {
        //默认算法SHA-512
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        //私盐，默认无
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        //是否生成公盐，默认false
        hashService.setGeneratePublicSalt(true);
        //用于生成公盐。默认就这个
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        //生成Hash值的迭代次数
        hashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        log.info(hex);
    }

    @Test
    public void aes() {
        AesCipherService aesCipherService = new AesCipherService();
        //设置key长度
        aesCipherService.setKeySize(128);
        //生成key
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        //加密
        String encrptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        log.info(encrptText);
        //解密
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        log.info(text2);
    }
}
