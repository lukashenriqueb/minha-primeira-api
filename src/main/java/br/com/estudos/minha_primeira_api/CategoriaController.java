package br.com.estudos.minha_primeira_api;

import br.com.estudos.minha_primeira_api.dto.ProdutoDTO;
import jakarta.validation.Valid;
import br.com.estudos.minha_primeira_api.dto.CategoriaDTO;
import br.com.estudos.minha_primeira_api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> listarTodos() {
        return categoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    public CategoriaDTO getCategoriaPorId(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @PostMapping
    public CategoriaDTO criar(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        return categoriaService.criar(categoriaDTO);
    }

    @PutMapping("/{id}")
    public CategoriaDTO atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaDTO categoriaDTO) {
        return categoriaService.atualizar(id, categoriaDTO);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
    }

}
