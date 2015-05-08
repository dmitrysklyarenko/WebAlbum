package org.ds.webalbum.service;

import org.ds.webalbum.dao.AlbumCatalogDao;
import org.ds.webalbum.dao.AlbumDao;
import org.ds.webalbum.dao.AlbumDaoException;
import org.ds.webalbum.dao.DaoException;
import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.AlbumCatalog;
import org.ds.webalbum.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Configurable
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private AlbumCatalogDao albumCatalogDao;

    @Override
    @Transactional
    public void addAlbum(Album album) {
        albumDao.create(album);
    }

    @Override
    @Transactional
    public void updateAlbum(Album album) {
        albumDao.update(album);
    }

    @Override
    @Transactional
    public Album getAlbum(int id) throws DaoException {
        return albumDao.findOne(id);
    }

    @Override
    @Transactional
    public List<Album> getAlbumsFromCatalog(Catalog catalog) throws AlbumDaoException {
        return albumCatalogDao.getAlbumsFromCatalog(catalog);
    }

    @Override
    @Transactional
    public void putAlbumToCatalog(Album album, Catalog catalog) {
        AlbumCatalog albumCatalog = new AlbumCatalog(album, catalog);
        albumCatalogDao.create(albumCatalog);
    }

    @Override
    @Transactional
    public void removeAlbumFromCatalog(Album album, Catalog catalog) {
        AlbumCatalog albumCatalog = new AlbumCatalog(album, catalog);
        albumCatalogDao.delete(albumCatalog);
    }

    @Override
    @Transactional
    public void deleteAlbum(Album album) {
        albumDao.delete(album);
    }

    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public void setAlbumCatalogDao(AlbumCatalogDao albumCatalogDao) {
        this.albumCatalogDao = albumCatalogDao;
    }
}
