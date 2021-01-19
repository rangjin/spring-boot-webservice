package org.rangjin.springbootwebservice.config.auth;

import lombok.RequiredArgsConstructor;
import org.rangjin.springbootwebservice.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// Spring Security 설정 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면 사용을 위해 해당 옵션들을 비활성화 함
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // URL 별 관리 설정 옵션
                    .authorizeRequests()
                    // 해당 URL 은 전체 열람 권한을 가짐
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    // 해당 URL 은 USER 권한을 가진 사람만 가능
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 나머지 URL 은 인증된 사용자만 가능
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 설정
                    .logout()
                        // 로그아웃 성공시 이동 URL
                        .logoutSuccessUrl("/")
                .and()
                    // OAuth2 로그인 기능에 대한 여러 설정
                    .oauth2Login()
                        // 로그인 성공 이후 사용자 정보를 가져올 때의 설정
                        .userInfoEndpoint()
                            // 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체
                            .userService(customOAuth2UserService);
    }
}
