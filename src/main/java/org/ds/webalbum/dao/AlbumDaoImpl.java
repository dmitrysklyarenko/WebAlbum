package org.ds.webalbum.dao;

import org.ds.webalbum.model.Album;
import org.hibernate.SessionFactory;

import java.util.List;


public class AlbumDaoImpl extends AbstractDao<Album, Integer> implements AlbumDao {

    @Override
    public void create(Album album) {
        super.create(album);
    }

    @Override
    public void update(Album album) {
        super.update(album);
    }

    @Override
    public void delete(Album album) {
        super.delete(album);
    }

    @Override
    public List<Album> findAll() {
        return super.findAll();
    }

    @Override
    public Album findOne(Integer id) throws DaoException {
        return super.findOne(id);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        super.sessionFactory = sessionFactory;
    }
}
