package br.com.estudos.minha_primeira_api.config;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.config.Customizer.withDefaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info(">>>> Criando o PasswordEncoder Bean (BCrypt)...");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        log.info(">>>> Criando InMemoryUserDetailsManager Bean...");
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info(">>>> Configurando o SegurityFilterChain...");
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/produtos", "/produtos/**", "/categorias/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/produtos").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/produtos/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/produtos/**").hasRole("ADMIN")
                                .requestMatchers("/categorias/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                        )
                .httpBasic(withDefaults())
        .csrf(csrf -> csrf.disable())
        .formLogin(form -> form.disable());
        return http.build();
    }
}
