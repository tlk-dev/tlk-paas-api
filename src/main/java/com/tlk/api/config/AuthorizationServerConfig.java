package com.tlk.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * OAuth2 설정 클래스
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${basic.auth.client.id}")
    private String basicAuthClientId;

    @Value("${basic.auth.client.password}")
    private String basicAuthClientPassword;

    @Autowired
    private DataSource dataSource;

    @Override public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient(basicAuthClientId)
//                .secret(passwordEncoder.encode(basicAuthClientPassword))
//                .authorizedGrantTypes("authorization_code", "password")
//                .scopes("read", "write")
//                .accessTokenValiditySeconds(60 * 60 * 12)   //12시간
//                .autoApprove(true);
        System.out.println(passwordEncoder.encode(basicAuthClientPassword));
//        clients.jdbc(dataSource)
//                .withClient(basicAuthClientId)
//                .secret(passwordEncoder.encode(basicAuthClientPassword))
//                .authorizedGrantTypes("authorization_code", "password")
//                .scopes("read", "write");
        clients.jdbc(dataSource);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       // endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
//        endpoints.tokenStore(tokenStore)
//                .authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService);
        endpoints //(4)
                .approvalStore(approvalStore())
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
        ;
    }
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ApprovalStore approvalStore() { //(3)
        return new JdbcApprovalStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()");   //(/token/check_token 접근 설정) param : ?token=value
    }
}
