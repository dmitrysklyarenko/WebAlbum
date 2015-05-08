package org.ds.webalbum.dao;

import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.AlbumCatalog;
import org.ds.webalbum.model.Catalog;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AlbumCatalogDaoImpl extends AbstractDao<AlbumCatalog, Integer> implements AlbumCatalogDao {
    @Override
    public void create(AlbumCatalog albumCatalog) {
        super.create(albumCatalog);
    }

    @Override
    public void update(AlbumCatalog albumCatalog) {
        super.update(albumCatalog);
    }

    @Override
    public void delete(AlbumCatalog albumCatalog) {
        super.delete(albumCatalog);
    }

    @Override
    public List<AlbumCatalog> findAll() {
        return super.findAll();
    }

    @Override
    public AlbumCatalog findOne(Integer id) throws DaoException {
        return super.findOne(id);
    }

    @Override
    public List<Album> getAlbumsFromCatalog(Catalog catalog) throws AlbumDaoException {
        Session session = sessionFactory.getCurrentSession();
        Query query = (Query) session.createQuery("select albumCatalog.album from AlbumCatalog as albumCatalog where catalogId = :catalogId")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setParameter("catalogId", catalog.getId());
        List<Album> albumList = query.list();
        if(albumList.size() > 0) {
            return albumList;
        } else {
            throw new AlbumDaoException("There are no albums in the catalog!");
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        super.sessionFactory = sessionFactory;
    }
}
