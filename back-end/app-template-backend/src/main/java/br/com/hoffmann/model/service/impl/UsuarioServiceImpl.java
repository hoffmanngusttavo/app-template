package br.com.hoffmann.model.service.impl;

import br.com.hoffmann.model.entity.Usuario;
import br.com.hoffmann.model.service.UsuarioService;
import br.com.hoffmann.model.service.generic.impl.GenericCrudServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UsuarioServiceImpl extends GenericCrudServiceImpl<Usuario> implements UsuarioService {






}
