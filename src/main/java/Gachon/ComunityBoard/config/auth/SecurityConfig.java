package Gachon.ComunityBoard.config.auth;


import Gachon.ComunityBoard.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                //.headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
//                    .antMatchers("/**","/css/**","/js/**","/images/**","/api/board/search/**","/api/board/all","/api/loginedUser").permitAll()// 모두에게 허용
//                    .antMatchers("/api/**").hasRole(Role.USER.name()) // User권한가진사람한테만 허용
//                    .anyRequest().authenticated() //나머지페이지는 인증된 사람에게만 허용
                    //.antMatchers("/api/board/posts/**","/api/user/userInfo/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/api/board")
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("/api/board")
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
