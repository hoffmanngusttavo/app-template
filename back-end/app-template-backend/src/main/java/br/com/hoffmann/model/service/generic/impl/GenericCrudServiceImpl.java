package br.com.hoffmann.model.service.generic.impl;

import br.com.hoffmann.model.entity.base.BaseEntity;
import br.com.hoffmann.model.repository.generic.GenericCrudRepository;
import br.com.hoffmann.model.service.exception.EntityNotFoundException;
import br.com.hoffmann.model.service.exception.ServiceException;
import br.com.hoffmann.model.service.generic.GenericCrudService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


public class GenericCrudServiceImpl <T extends BaseEntity, R extends GenericCrudRepository<T>>
        implements GenericCrudService<T> {


   protected final R repository;

    public GenericCrudServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T save(T entity) {
        try {
           return repository.save(entity);
        } catch (DataIntegrityViolationException ex){
            throw ex;
        } catch (Exception ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Id not found "+id);
        }
        repository.deleteById(id);
    }

    @Override
    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id not found "+ id));
    }

    @Override
    public Page<T> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
