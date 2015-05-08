package org.ds.webalbum.dao;

import org.ds.webalbum.model.Photo;
import org.hibernate.SessionFactory;

import java.util.List;


public class PhotoDaoImpl extends AbstractDao<Photo, Integer> implements PhotoDao {
    @Override
    public void create(Photo photo) {
        super.create(photo);
    }

    @Override
    public void update(Photo photo) {
        super.update(photo);
    }

    @Override
    public void delete(Photo photo) {
        super.delete(photo);
    }

    @Override
    public List<Photo> findAll() {
        return super.findAll();
    }

    @Override
    public Photo findOne(Integer id) throws DaoException {
        return super.findOne(id);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        super.sessionFactory = sessionFactory;
    }
}
