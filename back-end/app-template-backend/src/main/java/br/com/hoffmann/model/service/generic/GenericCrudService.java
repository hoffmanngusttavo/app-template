package br.com.hoffmann.model.service.generic;

import br.com.hoffmann.model.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericCrudService <T extends BaseEntity>{


    T save (T entity);

    void deleteById(Long id);

    T findById(Long id);

    Page<T> findAllPageable(Pageable pageable);


}
