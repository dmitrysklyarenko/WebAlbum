package org.ds.webalbum.dao;

import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.AlbumCatalog;
import org.ds.webalbum.model.Catalog;

import java.util.List;


public interface AlbumCatalogDao extends Dao<AlbumCatalog, Integer> {

    public List<Album> getAlbumsFromCatalog(Catalog catalog) throws AlbumDaoException;
}
