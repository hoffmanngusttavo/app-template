package br.com.hoffmann.model.repository;

import br.com.hoffmann.model.entity.Usuario;
import br.com.hoffmann.model.repository.generic.GenericCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends GenericCrudRepository<Usuario> {


    boolean existsByLogin(String login);
}
