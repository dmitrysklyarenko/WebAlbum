package org.ds.webalbum.service;

import org.ds.webalbum.dao.AlbumDaoException;
import org.ds.webalbum.dao.DaoException;
import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.Catalog;

import java.util.List;


public interface AlbumService {

    public void addAlbum(Album album);

    public void updateAlbum(Album album);

    public Album getAlbum(int id) throws DaoException;

    public List<Album> getAlbumsFromCatalog(Catalog catalog) throws AlbumDaoException;

    public void putAlbumToCatalog(Album album, Catalog catalog);

    public void removeAlbumFromCatalog(Album album, Catalog catalog);

    public void deleteAlbum(Album album);
}
