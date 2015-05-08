package org.ds.webalbum.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, PK extends Serializable> {

    public void create(T t);

    public void update(T t);

    public void delete(T t);

    public List<T> findAll();

    public T findOne(PK id) throws DaoException;
}