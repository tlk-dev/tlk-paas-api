package com.tlk.api.config;

import com.tlk.api.jpa.UserJpa;
import com.tlk.api.service.UserService;
import com.tlk.api.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

/**
 * OAuth 인증 커스터마이징(DB검증)
 */
@Configuration
@RequiredArgsConstructor
public class OauthAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        //DB 사용자정보 테이블 정보 조회
        UserJpa userInfo = userService.getUser(username, password);
        if (userInfo == null) {
            throw new BadCredentialsException(username);
        }
        /**
         * TODO 정상 토큰 발급 시 사용자 테이블에 토큰정보 업데이트 기능 추가
         */
        return new UsernamePasswordAuthenticationToken(username, password, getAuthorities());
    }

    @Override public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
