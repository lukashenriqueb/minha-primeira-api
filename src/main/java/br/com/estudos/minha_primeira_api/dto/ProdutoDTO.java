package br.com.estudos.minha_primeira_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class ProdutoDTO {

    private Long id;

    @NotNull(message = "O preço não pode ser nulo.")
    @Positive(message = "O preço deve ser um valor positivo.")
    private Double preco;

    @NotBlank(message = "O nome não pode estar em branco.")
    private String nome;

    public ProdutoDTO() {}
    public ProdutoDTO(Long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    public Long getId() {return id; }
    public void setId(Long id) {this.id = id; }
    public String getNome() {return nome; }
    public void setNome(String nome) {this.nome = nome; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) {this.preco = preco; }
}
