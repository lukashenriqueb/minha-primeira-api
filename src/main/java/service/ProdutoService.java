package br.com.estudos.minha_primeira_api.service;

import br.com.estudos.minha_primeira_api.dto.ProdutoDTO;
import br.com.estudos.minha_primeira_api.model.Produto;
import br.com.estudos.minha_primeira_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return  produtos.stream()
                .map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getPreco()))
                .collect(Collectors.toList());
    }
    public ProdutoDTO criar(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());

        Produto produtoSalvo = produtoRepository.save(produto);
        return new ProdutoDTO(produtoSalvo.getId(), produtoSalvo.getNome(), produtoSalvo.getPreco());
    }
    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoAtualizadoDTO) {
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoAtualizadoDTO.getNome());
                    produtoExistente.setPreco(produtoAtualizadoDTO.getPreco());

                    Produto produtoSalvo = produtoRepository.save(produtoExistente);
                    return new ProdutoDTO(produtoSalvo.getId(), produtoSalvo.getNome(), produtoSalvo.getPreco());
                })
                .orElse(null);
    }
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
    // Dentro da sua classe ProdutoService.java

    public ProdutoDTO findById(Long id) {
        // Busca no repositório (que retorna um Optional<Produto>)
        return produtoRepository.findById(id)
                // Se encontrar, converte a Entidade 'Produto' para um 'ProdutoDTO'
                .map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco()))
                // Se o findById não encontrar nada, retorna nulo
                .orElse(null);
    }
}
