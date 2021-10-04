package Gachon.ComunityBoard.config.auth;


import Gachon.ComunityBoard.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
//                    .antMatchers("/","/css/**","/js/**","/images/**","/board").permitAll()// 모두에게 허용
//                    .antMatchers("/api/**").hasRole(Role.USER.name()) // User권한가진사람한테만 허용
//                    .anyRequest().authenticated() //나머지페이지는 인증된 사람에게만 허용
                    .antMatchers("/api/board/**","/api/user/userInfo/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }

}
