package br.com.estudos.minha_primeira_api.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {

    private Long id;
    private Long categoriaId;



    @NotBlank(message = "A categoria não pode estar em branco")
    private String nome;

public CategoriaDTO() {}
public CategoriaDTO(Long id, String nome) {
    this.id = id;
    this.nome = nome;
    this.categoriaId = categoriaId;
}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

}
