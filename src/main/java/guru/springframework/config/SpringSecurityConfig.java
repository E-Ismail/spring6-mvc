package guru.springframework.config;

/*
 * @author Ech-Cherrate Ismail
 * @project spring-6-rest-mvc
 * @create 23/11/2025 - 13:35
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                //.httpBasic(Customizer.withDefaults()) //no more using basic
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults()))
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer.ignoringRequestMatchers("/api/**"));


        //Syntax changed
        //http.csrf().ignoringRequestMatchers("/api/**");
        return http.build();

    }
}
