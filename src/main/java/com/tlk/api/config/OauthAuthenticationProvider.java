package com.tlk.api.config;

import com.tlk.api.jpa.member.MemberJpa;
import com.tlk.api.service.MemberService;
import com.tlk.api.service.UserService;
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

    @Autowired
    private MemberService memberService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = (String) authentication.getPrincipal();
        String loginPassword = (String) authentication.getCredentials();
        //DB 사용자정보 테이블 정보 조회
        //UserJpa userInfo = userService.getUser(username, password);
        MemberJpa memberInfo = memberService.getMember(loginId, loginPassword);
        if (memberInfo == null) {
            throw new BadCredentialsException(loginId);
        }
        /**
         * TODO 정상 토큰 발급 시 사용자 테이블에 토큰정보 업데이트 기능 추가
         */
        return new UsernamePasswordAuthenticationToken(loginId, loginPassword, getAuthorities());
    }

    @Override public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
