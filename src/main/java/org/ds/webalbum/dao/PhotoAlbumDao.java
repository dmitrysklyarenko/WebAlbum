package org.ds.webalbum.dao;

import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.Photo;
import org.ds.webalbum.model.PhotoAlbum;

import java.util.List;


public interface PhotoAlbumDao extends Dao<PhotoAlbum, Integer> {

    public List<Photo> getPhotosByAlbum(Album album) throws PhotoDaoException;
}
