package jp.co.sss.cytech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jp.co.sss.cytech.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
             
                .requestMatchers("/user/register").permitAll() // user/registerはログインなしでOK
                
                .anyRequest().authenticated() // それ以外の画面はログインが必須
            )
            .formLogin(login -> login
                .loginPage("/user/login") // ログインページはここって明記してる
                
                .loginProcessingUrl("/user/login") // ログイン画面のth:actionと連動してる
                
                .defaultSuccessUrl("/user/top", true) // ログインが成功したときのURLを指定してる
                
                .permitAll() // ほかの画面はログイン不要
            )
            .userDetailsService(customUserDetailsService);;

        return http.build();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return NoOpPasswordEncoder.getInstance();
	}
}
