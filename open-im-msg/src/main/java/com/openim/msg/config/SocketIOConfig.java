package com.openim.msg.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Configuration
@Slf4j
public class SocketIOConfig {

    @Resource
    private OAuth2ResourceServerProperties oAuth2ResourceServerProperties;
    @Resource
    private StreamBridge streamBridge;
    @Value("${spring.cloud.stream.bindings.message-in-0.destination}")
    private String bindingName;

    @Value("${socketio.port}")
    private Integer port;

    @Value("${socketio.bossCount}")
    private int bossCount;

    @Value("${socketio.workCount}")
    private int workCount;

    @Value("${socketio.allowCustomRequests}")
    private boolean allowCustomRequests;

    @Value("${socketio.upgradeTimeout}")
    private int upgradeTimeout;

    @Value("${socketio.pingTimeout}")
    private int pingTimeout;

    @Value("${socketio.pingInterval}")
    private int pingInterval;


    @Value("${socketio.public-key}")
    private String publicKey;

    private final static KeyFactory keyFactory;

    static {
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    @Bean
    @SneakyThrows
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        // 不可指定hostname，指定之后无法通过网关负载均衡
        config.setPort(port);
        config.setTransports(Transport.WEBSOCKET);
        config.setAuthorizationListener(data -> {
            log.info("AuthorizationListener: {}", data);
            String token = data.getSingleUrlParam("token");
            if (!StringUtils.hasText(token)) {
                return false;
            }
            verifyToken(token);
            return true;
        });

        return new SocketIOServer(config);
    }


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
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(org.apache.commons.codec.binary.Base64.decodeBase64(pem));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);
        return publicKey;
    }


    /**
     * 验证jwt
     *
     * @param token
     * @throws Exception
     */
    private void verifyToken(String token) {

        try {
            PublicKey publicKey1 = getPublicKey(publicKey);

            JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime()
                    .setVerificationKey(publicKey1)
                    .setExpectedAudience("messaging-client")//用于验证签名是否合法，可以设置多个，且可设置必须存在项，如果jwt中不包含这些内容则不通过
                    .build();

            jwtConsumer.processToClaims(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 用于扫描netty-socketio的注解，比如 @OnConnect、@OnEvent
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }
}