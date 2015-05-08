package org.ds.webalbum.service;

import org.ds.webalbum.dao.DaoException;
import org.ds.webalbum.model.Catalog;


public interface CatalogService {

    public void addCatalog(Catalog catalog);

    public void updateCatalog(Catalog catalog);

    public Catalog getCatalog(int id) throws DaoException;

    public void deleteCatalog(Catalog catalog);
}
