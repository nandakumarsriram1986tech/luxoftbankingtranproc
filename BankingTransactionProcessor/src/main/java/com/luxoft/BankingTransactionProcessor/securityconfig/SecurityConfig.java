package com.luxoft.BankingTransactionProcessor.securityconfig;

//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;*/

//@Configuration
public class SecurityConfig {

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Safely opens up the H2 console path automatically
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated()
                )
                // H2 console uses a database login form; CSRF protection must be bypassed for it
                .csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))

                // H2 console uses <iframe> elements which Spring Security blocks by default
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }*/

  /*  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Use standard antMatchers / requestMatchers style strings instead of the PathRequest helper
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }*/
}



