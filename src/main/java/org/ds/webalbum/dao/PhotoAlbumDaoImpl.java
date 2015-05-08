package org.ds.webalbum.dao;

import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.Photo;
import org.ds.webalbum.model.PhotoAlbum;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class PhotoAlbumDaoImpl extends AbstractDao<PhotoAlbum, Integer> implements PhotoAlbumDao {
    @Override
    public void create(PhotoAlbum photoAlbum) {
        super.create(photoAlbum);
    }

    @Override
    public void update(PhotoAlbum photoAlbum) {
        super.update(photoAlbum);
    }

    @Override
    public void delete(PhotoAlbum photoAlbum) {
        super.delete(photoAlbum);
    }

    @Override
    public List<PhotoAlbum> findAll() {
        return super.findAll();
    }

    @Override
    public PhotoAlbum findOne(Integer id) throws DaoException {
        return super.findOne(id);
    }

    @Override
    public List<Photo> getPhotosByAlbum(Album album) throws PhotoDaoException {
        Session session = sessionFactory.getCurrentSession();
        Query query = (Query) session.createQuery("select photoAlbum.photo from PhotoAlbum as photoAlbum where albumId = :albumId")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter("albumId", album.getId());
        List<Photo> photoList = query.list();
        if(photoList.size() > 0) {
            return photoList;
        } else {
            throw new PhotoDaoException("There are no photo in the album!");
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        super.sessionFactory = sessionFactory;
    }
}
