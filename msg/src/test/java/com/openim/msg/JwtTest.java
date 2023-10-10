package com.openim.msg;

import org.apache.commons.codec.binary.Base64;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class JwtTest {

    private static String public_Key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzXi34N+Q/KZjqBv1FnSvdYj2FGs8sglnFX75Q5XCANi7/9WEPiRLwc/8/OUTLFsHb/kYOQ9mXAP9Af/UN549PB1xE3KQ4NDQh8xqqv8bx1ynmSRrIjYXPu4Sm7UIOloR/eXtoLKxXgl1PtT7kUjJbIF6IB7tBB0RfAB0NFvqbrrGA7ETAUYZyceKLSOIwdYwcHpS6dKoa+Wcyr9sSFAKoAbK8L8rOZk6twxIXYaruI+a+Uc96f/tne3+mCrQaNGTD5HdptQdZ5+45qupWO7vGMZLQX/lxdATsoqlyjU3u2XcHAo8x/0AVHL7sN45kP0LzYGuyBsEvjmK0b7Qp/MrqQIDAQAB";


    /**
     * 获取PublicKey对象
     *
     * @param publicKeyBase64
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PublicKey getPublicKey(String publicKeyBase64)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String pem = publicKeyBase64.replaceAll("\\-*BEGIN PUBLIC KEY\\-*", "").replaceAll("\\-*END PUBLIC KEY\\-*", "")
                .trim();
        java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(pem));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);
        System.out.println(publicKey);
        return publicKey;
    }


    /**
     * 验证jwt
     *
     * @param token
     * @return
     * @throws Exception
     */
    private static JwtClaims verifyToken(String token) {

        try {
            PublicKey publicKey = getPublicKey(public_Key);

            JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime()
                    .setVerificationKey(publicKey)
                    .setExpectedAudience("messaging-client")//用于验证签名是否合法，可以设置多个，且可设置必须存在项，如果jwt中不包含这些内容则不通过
                    .build();

            return jwtConsumer.processToClaims(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) {

        String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6Im1lc3NhZ2luZy1jbGllbnQiLCJuYmYiOjE2OTY5NTA1NzEsInNjb3BlIjpbIm1lc3NhZ2UucmVhZCJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJleHAiOjE2OTY5NTIzNzEsImlhdCI6MTY5Njk1MDU3MX0.Blvn6LmsPRN5K36KMfwRyH1Sgh9EMrS6G8Ju2pKqwJQhteOqJydYHp8bh8Bp5xyPZO7n_-Zn8dYJvrnG2y5AclItSg-fMXdw-AcJFvTOS89l4tSnyHVsY_tFkHrBi0p4huFjUzeLHHy8Jm2uqG1gnQTJv_5upqUu7rsmZ6ZqqA8xzRrGvWLjAJmtoHom6wMLW9uDS_Y4CXNs0zRaA2wZe_opaqfiPHmeTCkxN671bQ-qirXNIztOSlU6YEqTkc7QD9TBQ2KJUgwU3L6vCpf5UH-KNlwqpSGBtdgTiav1DMPQbk0WfUVxbVP9hk_ffxwKzLHUZBlJ51KhIpRo8whDjg";
        try {
            verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
