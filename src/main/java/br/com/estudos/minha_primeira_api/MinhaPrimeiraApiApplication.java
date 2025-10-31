package br.com.estudos.minha_primeira_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MinhaPrimeiraApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinhaPrimeiraApiApplication.class, args);
    }
}
