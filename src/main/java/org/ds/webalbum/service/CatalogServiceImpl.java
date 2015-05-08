package org.ds.webalbum.service;

import org.ds.webalbum.dao.CatalogDao;
import org.ds.webalbum.dao.DaoException;
import org.ds.webalbum.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Configurable
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Override
    @Transactional
    public void addCatalog(Catalog catalog) {
        catalogDao.create(catalog);
    }

    @Override
    @Transactional
    public void updateCatalog(Catalog catalog) {
        catalogDao.update(catalog);
    }

    @Override
    @Transactional
    public Catalog getCatalog(int id) throws DaoException {
        return catalogDao.findOne(id);
    }

    @Override
    @Transactional
    public void deleteCatalog(Catalog catalog) {
        catalogDao.delete(catalog);
    }

    public void setCatalogDao(CatalogDao catalogDao) {
        this.catalogDao = catalogDao;
    }
}
