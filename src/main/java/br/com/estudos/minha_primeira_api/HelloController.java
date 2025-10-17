package br.com.estudos.minha_primeira_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/ola")
    public String dizerOla() {
        return "Olá Mundo! Minha Primeira API com Spring Boot está no ar!";
    }
}
