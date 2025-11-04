package br.com.estudos.minha_primeira_api.model;
import br.com.estudos.minha_primeira_api.model.Produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
public class Categoria {

    public Long getId;
    public String getNome;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Produto> produtos;



    public Categoria() {
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
}
