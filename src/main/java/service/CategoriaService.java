package br.com.estudos.minha_primeira_api.service;

import br.com.estudos.minha_primeira_api.dto.CategoriaDTO;
import br.com.estudos.minha_primeira_api.dto.ProdutoDTO;
import br.com.estudos.minha_primeira_api.model.Categoria;
import br.com.estudos.minha_primeira_api.model.Produto;
import br.com.estudos.minha_primeira_api.repository.ProdutoRepository;
import br.com.estudos.minha_primeira_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarTodos() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(c -> new CategoriaDTO(c.getId(), c.getNome()))
                .collect(Collectors.toList());
    }

    public CategoriaDTO criar(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());

        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoriaSalva.getId(), categoriaSalva.getNome());
    }

    public CategoriaDTO atualizar(Long id, CategoriaDTO categoriaAtualizadaDTO) {
        return categoriaRepository.findById(id)
        .map(categoriaExistente -> {
        categoriaExistente.setNome(categoriaAtualizadaDTO.getNome());

        Categoria categoriaSalva = categoriaRepository.save(categoriaExistente);
        return new CategoriaDTO(categoriaSalva.getId(), categoriaSalva.getNome());
        })
                .orElse(null);
    }

    public void deletar(Long id) { categoriaRepository.deleteById(id); }

    public CategoriaDTO findById(Long id) {
    return categoriaRepository.findById(id)
            .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNome()))
            .orElse(null);
    }
}
