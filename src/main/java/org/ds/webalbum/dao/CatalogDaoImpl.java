package org.ds.webalbum.dao;

import org.ds.webalbum.model.Catalog;
import org.hibernate.SessionFactory;

import java.util.List;


public class CatalogDaoImpl extends AbstractDao<Catalog, Integer> implements CatalogDao {
    @Override
    public void create(Catalog catalog) {
        super.create(catalog);
    }

    @Override
    public void update(Catalog catalog) {
        super.update(catalog);
    }

    @Override
    public void delete(Catalog catalog) {
        super.delete(catalog);
    }

    @Override
    public List<Catalog> findAll() {
        return super.findAll();
    }

    @Override
    public Catalog findOne(Integer id) throws DaoException {
        return super.findOne(id);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        super.sessionFactory = sessionFactory;
    }
}
