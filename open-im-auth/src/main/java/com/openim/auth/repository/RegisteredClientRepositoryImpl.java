package com.openim.auth.repository;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.openim.auth.entity.Oauth2RegisteredClient;
import com.openim.auth.service.IOauth2RegisteredClientService;
import jakarta.annotation.Resource;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 客户端
 */
@Component
public class RegisteredClientRepositoryImpl implements RegisteredClientRepository {

    @Resource
    private IOauth2RegisteredClientService registeredClientService;

    @Override
    public void save(RegisteredClient registeredClient) {
        Oauth2RegisteredClient client = new Oauth2RegisteredClient();
        client.setId(Long.valueOf(registeredClient.getId()));
        client.setClientId(registeredClient.getId());
        client.setClientIdIssuedAt(LocalDateTime.ofInstant(Objects.requireNonNull(registeredClient.getClientIdIssuedAt()), ZoneId.systemDefault()));
        client.setClientSecret(registeredClient.getClientSecret());
        client.setClientSecretExpiresAt(LocalDateTime.ofInstant(Objects.requireNonNull(registeredClient.getClientSecretExpiresAt()), ZoneId.systemDefault()));
        client.setClientName(registeredClient.getClientName());
        client.setClientAuthenticationMethods(registeredClient.getClientAuthenticationMethods().stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.joining(",")));
        client.setAuthorizationGrantTypes(registeredClient.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::getValue).collect(Collectors.joining(",")));
        client.setRedirectUris(String.join(",", registeredClient.getRedirectUris()));
        client.setPostLogoutRedirectUris(String.join(",", registeredClient.getPostLogoutRedirectUris()));
        client.setScopes(String.join(",", registeredClient.getScopes()));
        client.setClientSettings(JSON.toJSONString(registeredClient.getClientSettings()));
        client.setTokenSettings(JSON.toJSONString(registeredClient.getTokenSettings()));
        client.setCreateUser(1L, "admin");
        client.setUpdaterUser(1L, "admin");
        client.setSourceFrom("system");
        client.setDeleted(false);
        registeredClientService.save(client);

    }

    @Override
    public RegisteredClient findById(String id) {
        Oauth2RegisteredClient registeredClient = registeredClientService.getById(Long.valueOf(id));
        return getRegisteredClient(registeredClient);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        LambdaQueryWrapper<Oauth2RegisteredClient> wrapper = Wrappers.lambdaQuery(Oauth2RegisteredClient.class);
        Oauth2RegisteredClient registeredClient = registeredClientService.getOne(wrapper.eq(Oauth2RegisteredClient::getClientId, clientId));
        return getRegisteredClient(registeredClient);
    }

    /**
     * 转换配置信息
     *
     * @param registeredClient
     * @return
     */
    public RegisteredClient getRegisteredClient(Oauth2RegisteredClient registeredClient) {
        return RegisteredClient.withId(registeredClient.getId().toString())
                .clientId(registeredClient.getClientId())
                .clientIdIssuedAt(registeredClient.getClientIdIssuedAt().toInstant(ZoneOffset.UTC))
                .clientSecret(registeredClient.getClientSecret())
                .clientSecretExpiresAt(registeredClient.getClientSecretExpiresAt().toInstant(ZoneOffset.UTC))
                .clientName(registeredClient.getClientName())
                .authorizationGrantTypes(n -> {
                    Arrays.stream(registeredClient.getAuthorizationGrantTypes().split(",")).forEach(e -> {
                        n.add(new AuthorizationGrantType(e));
                    });
                })
                .clientAuthenticationMethods(n -> Arrays.stream(registeredClient.getClientAuthenticationMethods().split(",")).forEach(e -> {
                    n.add(new ClientAuthenticationMethod(e));
                }))
                .redirectUris(n -> n.addAll(Arrays.asList(registeredClient.getRedirectUris().split(","))))
                .postLogoutRedirectUris(n -> n.addAll(Arrays.asList(registeredClient.getPostLogoutRedirectUris().split(","))))
                .scopes(n -> n.addAll(Arrays.asList(registeredClient.getScopes().split(","))))
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true)
                        .requireProofKey(false)
                        .build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                        .idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
                        .accessTokenTimeToLive(Duration.ofSeconds(30 * 60))
                        .refreshTokenTimeToLive(Duration.ofSeconds(60 * 60))
                        .reuseRefreshTokens(false)
                        .build())
                .build();
    }
}
