package br.com.estudos.minha_primeira_api;

import jakarta.validation.Valid;
import br.com.estudos.minha_primeira_api.dto.ProdutoDTO;
import br.com.estudos.minha_primeira_api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService  produtoService;

    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoDTO getProdutoPorId(@PathVariable Long id) {
        return produtoService.findById(id);
    }

    @PostMapping
    public ProdutoDTO criar(@RequestBody @Valid ProdutoDTO produtoDTO) {
        return produtoService.criar(produtoDTO);
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produtoDTO) {
        return produtoService.atualizar(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }
}
