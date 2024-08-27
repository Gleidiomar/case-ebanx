package br.com.pedido.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.pedido.exception.EntityInvalidException;
import br.com.pedido.service.GenericService;
import br.com.pedido.service.MessagesService;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private JpaRepository<T, ID> repository;
    @Autowired
    public MessagesService messagesService;

    @Override
    public void saveOrUpdate(T entity) {
        try {
            repository.save(entity);
        } catch (ConstraintViolationException e) {
            throw new EntityInvalidException(e.getMessage());
        }
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<T> getAllPaginated(int page, int count, Sort.Direction direction, String sortProperty) {
    	return repository.findAll(PageRequest.of(page, count, Sort.by(direction, sortProperty)));
    }

    @Override
    public T get(ID id) {
        return repository.getOne(id);
    }

    @Override
    public T add(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public T addOrUpdate(T entity) {
        return repository.save(entity);
    }

    @Override
    public void updateEntities(T entities) {
        repository.save(entities);
    }

    @Override
    public void remove(T entity) {
        repository.delete(entity);
    }

    @Override
    public void removeById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void removeEntities(T entities) {
        repository.delete(entities);
    }

    @Override
    public void removeEntitiesById(List<ID> entityIds) {
        for (ID id : entityIds) {
            repository.deleteById(id);
        }
    }
}
