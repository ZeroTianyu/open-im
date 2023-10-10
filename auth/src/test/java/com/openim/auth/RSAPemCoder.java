//package com.openim.auth;
//
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
//
//import javax.crypto.Cipher;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.nio.charset.StandardCharsets;
//import java.security.*;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
///*
//1.使用openssl生成私钥和公钥
//    openssl生成私钥命令： openssl genrsa -out rsa_private_key.pem 1024
//    openssl生成公钥命令： openssl rsa -in rsa_private_key.pem -pubout -out rsa_public_key.pem
//2.此时可以看到 rsa_private_key.pem 和 rsa_public_key.pem 两个文件。这时候的私钥是不能直接使用的，需要进行 pkcs8 编码
//    openssl的pkcs8编码命令：openssl pkcs8 -topk8 -in rsa_private_key.pem -out pkcs8_rsa_private_key.pem -nocrypt
//    此时可以看到 pkcs8_rsa_private_key.pem 文件。
//    至此，可用的密钥对已经生成好了，私钥使用pkcs8_rsa_private_key.pem，公钥采用rsa_public_key.pem。
//3.使用密钥对进行签名、加解密
//    公钥加密数据，然后私钥解密的情况被称为加密和解密；
//    私钥加密数据，公钥解密一般被称为签名和验证签名。
// */
//
//public class RSAPemCoder {
//    public static final String PRIVATE_KEY_PEM = "F:/pkcs8_rsa_private_key.pem";
//    public static final String PUBLIC_KEY_PEM = "F:/rsa_public_key.pem";
//
//    public static final String KEY_SHA = "SHA";
//    public static final String KEY_MD5 = "MD5";
//    public static final String KEY_ALGORITHM = "RSA";
//    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
//
//
//    public static void main(String[] args) throws Exception {
//        PrivateKey privateKey = getPrivateKeyFromPem();
//        PublicKey publicKey = getPublicKeyFromPem();
//        String data = "测试123";
//
//        /*************************************************************************************************/
//        //私钥加密
//        byte[] privateDataBytes = encryptByPrivateKey(data.getBytes(StandardCharsets.UTF_8), privateKey);
//        String privateDataBase64 = encryptBASE64(privateDataBytes);
//        System.out.println(privateDataBase64);
//        //公钥解密
//        privateDataBytes = decryptBASE64(privateDataBase64);
//        byte[] dataBytes = decryptByPublicKey(privateDataBytes, publicKey);
//        System.out.println(new String(dataBytes, StandardCharsets.UTF_8));
//        /*************************************************************************************************/
//
//        /*************************************************************************************************/
//        //公钥加密
//        byte[] publicDataBytes = encryptByPublicKey(data.getBytes(StandardCharsets.UTF_8), publicKey);
//        String publicDataBase64 = encryptBASE64(publicDataBytes);
//        System.out.println(publicDataBase64);
//        //私钥解密
//        publicDataBytes = decryptBASE64(publicDataBase64);
//        dataBytes = decryptByPrivateKey(publicDataBytes, privateKey);
//        System.out.println(new String(dataBytes, StandardCharsets.UTF_8));
//        /*************************************************************************************************/
//
//        /*************************************************************************************************/
//        //签名：私钥加密
//        String sign = sign(data.getBytes(StandardCharsets.UTF_8), privateKey);
//        System.out.println(sign);
//        //验证签名：公钥解密
//        boolean verify = verify(data.getBytes(StandardCharsets.UTF_8), publicKey, sign);
//        System.out.println(verify);
//        /*************************************************************************************************/
//    }
//
//    /**
//     * 用私钥对信息生成数字签名
//     * @param data       加密数据
//     * @param privateKey 私钥
//     * @return
//     * @throws Exception
//     */
//    public static String sign(byte[] data, PrivateKey privateKey) throws Exception {
//        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//        signature.initSign(privateKey);
//        signature.update(data);
//        return encryptBASE64(signature.sign());
//    }
//
//    /**
//     * 校验数字签名
//     * @param data      加密数据
//     * @param publicKey 公钥
//     * @param sign      数字签名
//     * @return 校验成功返回true 失败返回false
//     * @throws Exception
//     */
//    public static boolean verify(byte[] data, PublicKey publicKey, String sign) throws Exception {
//        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
//        signature.initVerify(publicKey);
//        signature.update(data);
//        return signature.verify(decryptBASE64(sign));
//    }
//
//    /**
//     * 私钥解密
//     * @param data       密文
//     * @param privateKey 私钥
//     * @return
//     * @throws Exception
//     */
//    public static byte[] decryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        return cipher.doFinal(data);
//    }
//
//    /**
//     * 用公钥解密
//     * @param data      密文
//     * @param publicKey 公钥
//     * @return
//     * @throws Exception
//     */
//    public static byte[] decryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.DECRYPT_MODE, publicKey);
//        return cipher.doFinal(data);
//    }
//
//    /**
//     * 用公钥加密
//     * @param data      明文
//     * @param publicKey 公钥
//     * @return
//     * @throws Exception
//     */
//    public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        return cipher.doFinal(data);
//    }
//
//    /**
//     * 用私钥加密
//     * @param data       明文
//     * @param privateKey 私钥
//     * @return
//     * @throws Exception
//     */
//    public static byte[] encryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
//        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
//        return cipher.doFinal(data);
//    }
//
//    // 获取私匙
//    public static PrivateKey getPrivateKeyFromPem() throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader(PRIVATE_KEY_PEM));
//        String s = br.readLine();
//        String str = "";
//        s = br.readLine();
//        while (s.charAt(0) != '-') {
//            str += s + "\r";
//            s = br.readLine();
//        }
//        BASE64Decoder base64decoder = new BASE64Decoder();
//        byte[] b = base64decoder.decodeBuffer(str);
//
//        // 生成私匙
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);
//        PrivateKey privateKey = kf.generatePrivate(keySpec);
//        return privateKey;
//    }
//
//    // 获取公钥
//    public static PublicKey getPublicKeyFromPem() throws Exception {
//        BufferedReader br = new BufferedReader(new FileReader(PUBLIC_KEY_PEM));
//        String s = br.readLine();
//        String str = "";
//        s = br.readLine();
//        while (s.charAt(0) != '-') {
//            str += s + "\r";
//            s = br.readLine();
//        }
//        BASE64Decoder base64decoder = new BASE64Decoder();
//        byte[] b = base64decoder.decodeBuffer(str);
//        KeyFactory kf = KeyFactory.getInstance("RSA");
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(b);
//        PublicKey pubKey = kf.generatePublic(keySpec);
//        return pubKey;
//    }
//
//    public static byte[] decryptBASE64(String key) throws Exception {
//        return (new BASE64Decoder()).decodeBuffer(key);
//    }
//
//    public static String encryptBASE64(byte[] key) throws Exception {
//        return (new BASE64Encoder()).encodeBuffer(key);
//    }
//
//    public static byte[] encryptMD5(byte[] data) throws Exception {
//        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
//        md5.update(data);
//        return md5.digest();
//    }
//
//    public static byte[] encryptSHA(byte[] data) throws Exception {
//        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
//        sha.update(data);
//        return sha.digest();
//    }
//}