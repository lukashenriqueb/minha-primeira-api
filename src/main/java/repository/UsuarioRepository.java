package br.com.estudos.minha_primeira_api.repository;

import br.com.estudos.minha_primeira_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    java.util.Optional<Usuario> findByUsername(String username);
}
