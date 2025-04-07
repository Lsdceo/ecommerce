package com.revisao.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean  // Cria um bean para o encoder de senha.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Retorna o encoder BCrypt para criptografar senhas.
    }

    @Bean  // Cria um bean para o filtro de segurança.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable());  // Desativa a proteção CSRF.
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());  // Permite acesso a todas as requisições sem autenticação.
        return http.build();  // Constrói e retorna o filtro de segurança configurado.
    }

}
