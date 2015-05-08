package org.ds.webalbum.service;

import org.ds.webalbum.dao.DaoException;
import org.ds.webalbum.dao.PhotoDaoException;
import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.Photo;

import java.util.List;


public interface PhotoService {

    public void addPhoto(Photo photo);

    public void updatePhoto(Photo photo);

    public Photo getPhoto(int id) throws DaoException;

    public List<Photo> getPhotosFromAlbum(Album album) throws PhotoDaoException;

    public void putPhotoToAlbum(Photo photo, Album album);

    public void removePhotoFromAlbum(Photo photo, Album album);

    public void deletePhoto(Photo photo);

}
