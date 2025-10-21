package br.com.estudos.minha_primeira_api.service;

import br.com.estudos.minha_primeira_api.dto.ProdutoDTO;
import br.com.estudos.minha_primeira_api.model.Categoria;
import br.com.estudos.minha_primeira_api.model.Produto;
import br.com.estudos.minha_primeira_api.repository.CategoriaRepository; // Import necessário
import br.com.estudos.minha_primeira_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository; // Injeção correta

    public List<ProdutoDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(p -> new ProdutoDTO(
                        p.getId(),
                        p.getNome(),
                        p.getPreco(),
                        // Correção 1: Sintaxe correta do ternário
                        p.getCategoria() != null ? p.getCategoria().getId() : null
                ))
                .collect(Collectors.toList());
    }

    public ProdutoDTO criar(ProdutoDTO produtoDTO) {
        Long idCategoria = produtoDTO.getCategoriaId();
        if (idCategoria == null) {
            throw new RuntimeException("O ID da categoria é obrigatório para criar um produto.");
        }
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria com ID " + idCategoria + " não encontrada!"));

        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(categoria);

        Produto produtoSalvo = produtoRepository.save(produto);

        return new ProdutoDTO(
                produtoSalvo.getId(),
                produtoSalvo.getNome(),
                produtoSalvo.getPreco(),
                produtoSalvo.getCategoria().getId()
        );
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoAtualizadoDTO) {
        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoAtualizadoDTO.getNome());
                    produtoExistente.setPreco(produtoAtualizadoDTO.getPreco());

                    if (produtoAtualizadoDTO.getCategoriaId() != null &&
                            (produtoExistente.getCategoria() == null || !produtoExistente.getCategoria().getId().equals(produtoAtualizadoDTO.getCategoriaId()))) {
                        Categoria novaCategoria = categoriaRepository.findById(produtoAtualizadoDTO.getCategoriaId())
                                .orElseThrow(() -> new RuntimeException("Categoria com ID " + produtoAtualizadoDTO.getCategoriaId() + " não encontrada para atualização!"));
                        produtoExistente.setCategoria(novaCategoria);
                    }

                    Produto produtoSalvo = produtoRepository.save(produtoExistente);

                    return new ProdutoDTO(
                            produtoSalvo.getId(),
                            produtoSalvo.getNome(),
                            produtoSalvo.getPreco(),
                            produtoSalvo.getCategoria() != null ? produtoSalvo.getCategoria().getId() : null
                    );
                })
                .orElse(null);
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto com ID " + id + " não encontrado para deleção!");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO findById(Long id) {
        return produtoRepository.findById(id)
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getCategoria() != null ? produto.getCategoria().getId() : null
                ))
                .orElse(null);
    }
}