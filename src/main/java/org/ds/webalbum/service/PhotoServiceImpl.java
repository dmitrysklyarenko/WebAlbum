package org.ds.webalbum.service;

import org.ds.webalbum.dao.DaoException;
import org.ds.webalbum.dao.PhotoAlbumDao;
import org.ds.webalbum.dao.PhotoDao;
import org.ds.webalbum.dao.PhotoDaoException;
import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.Photo;
import org.ds.webalbum.model.PhotoAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Configurable
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private PhotoDao photoDao;
    @Autowired
    private PhotoAlbumDao photoAlbumDao;

    @Override
    @Transactional
    public void addPhoto(Photo photo) {
        photoDao.create(photo);
    }

    @Override
    @Transactional
    public void updatePhoto(Photo photo) {
        photoDao.update(photo);
    }

    @Override
    @Transactional
    public Photo getPhoto(int id) throws DaoException {
        return photoDao.findOne(id);
    }

    @Override
    @Transactional
    public List<Photo> getPhotosFromAlbum(Album album) throws PhotoDaoException {
        return photoAlbumDao.getPhotosByAlbum(album);
    }

    @Override
    @Transactional
    public void putPhotoToAlbum(Photo photo, Album album) {
        PhotoAlbum photoAlbum = new PhotoAlbum(photo, album);
        photoAlbumDao.create(photoAlbum);
    }

    @Override
    @Transactional
    public void removePhotoFromAlbum(Photo photo, Album album) {
        PhotoAlbum photoAlbum = new PhotoAlbum(photo, album);
        photoAlbumDao.delete(photoAlbum);
    }

    @Override
    @Transactional
    public void deletePhoto(Photo photo) {
        photoDao.delete(photo);
    }

    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    public void setPhotoAlbumDao(PhotoAlbumDao photoAlbumDao) {
        this.photoAlbumDao = photoAlbumDao;
    }
}
