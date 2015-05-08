package org.ds.webalbum.dao;

import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.AlbumCatalog;
import org.ds.webalbum.model.Catalog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*/testContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class AlbumCatalogDaoImplTest {

    @Autowired
    private AlbumCatalogDao albumCatalogDaoTest;

    @Test
    public void createAlbumCatalogTest() {
        Album album = new Album("album1");
        Catalog catalog = new Catalog("catalog1");
        AlbumCatalog albumCatalog = new AlbumCatalog(album, catalog);
        albumCatalogDaoTest.create(albumCatalog);
        assertEquals(albumCatalogDaoTest.findAll().get(0), albumCatalog);
    }

    @Test
    public void getListOfAlbumCatalogTest() {
        Album album1 = new Album("album1");
        Album album2 = new Album("album2");
        Catalog catalog1 = new Catalog("catalog1");
        Catalog catalog2 = new Catalog("catalog2");
        AlbumCatalog albumCatalog1 = new AlbumCatalog(album1, catalog1);
        AlbumCatalog albumCatalog2 = new AlbumCatalog(album2, catalog2);
        List<AlbumCatalog> albumCatalogList = new ArrayList<AlbumCatalog>();
        albumCatalogList.add(albumCatalog1);
        albumCatalogList.add(albumCatalog2);
        albumCatalogDaoTest.create(albumCatalog1);
        albumCatalogDaoTest.create(albumCatalog2);
        assertEquals(albumCatalogList, albumCatalogDaoTest.findAll());
    }

    @Test
    public void listOfAlbumCatalogIsNotEmpty() {
        Album album1 = new Album("album1");
        Album album2 = new Album("album2");
        Album album3 = new Album("album3");
        Catalog catalog1 = new Catalog("catalog1");
        Catalog catalog2 = new Catalog("catalog2");
        Catalog catalog3 = new Catalog("catalog3");
        AlbumCatalog albumCatalog1 = new AlbumCatalog(album1, catalog1);
        AlbumCatalog albumCatalog2 = new AlbumCatalog(album2, catalog2);
        AlbumCatalog albumCatalog3 = new AlbumCatalog(album3, catalog3);
        albumCatalogDaoTest.create(albumCatalog1);
        albumCatalogDaoTest.create(albumCatalog2);
        albumCatalogDaoTest.create(albumCatalog3);
        assertEquals(albumCatalogDaoTest.findAll().size(), 3);
    }

    @Test
    public void listOfAlbumCatalogIsEmpty() {
        assertEquals(albumCatalogDaoTest.findAll().size(), 0);
    }

    @Test
    public void getOneAlbumCatalogTest() throws DaoException {
        Album album1 = new Album("album1");
        Album album2 = new Album("album2");
        Album album3 = new Album("album3");
        Catalog catalog1 = new Catalog("catalog1");
        Catalog catalog2 = new Catalog("catalog2");
        Catalog catalog3 = new Catalog("catalog3");
        AlbumCatalog albumCatalog1 = new AlbumCatalog(album1, catalog1);
        AlbumCatalog albumCatalog2 = new AlbumCatalog(album2, catalog2);
        AlbumCatalog albumCatalog3 = new AlbumCatalog(album3, catalog3);
        List<AlbumCatalog> albumCatalogList = new ArrayList<AlbumCatalog>();
        albumCatalogList.add(albumCatalog1);
        albumCatalogList.add(albumCatalog2);
        albumCatalogList.add(albumCatalog3);
        albumCatalogDaoTest.create(albumCatalog1);
        albumCatalogDaoTest.create(albumCatalog2);
        albumCatalogDaoTest.create(albumCatalog3);
        assertEquals(albumCatalogList.get(1), albumCatalogDaoTest.findOne(2));
    }

    @Test(expected = DaoException.class)
    public void getNotExistingAlbumCatalogTest() throws DaoException {
        albumCatalogDaoTest.findOne(1);
    }

    @Test
    public void deleteAlbumCatalogTest() {
        Album album = new Album("album1");
        Catalog catalog = new Catalog("catalog1");
        AlbumCatalog albumCatalog1 = new AlbumCatalog(album, catalog);
        albumCatalogDaoTest.create(albumCatalog1);
        albumCatalogDaoTest.delete(albumCatalog1);
        assertEquals(albumCatalogDaoTest.findAll().size(), 0);
    }

    @Test
    public void updateAlbumCatalogTest() {
        Album album = new Album("album1");
        Catalog catalog = new Catalog("catalog1");
        AlbumCatalog albumCatalog1 = new AlbumCatalog(album, catalog);
        albumCatalogDaoTest.create(albumCatalog1);
        album.setCaption("albumTest");
        catalog.setCaption("testCatalog");
        albumCatalog1.setCatalog(catalog);
        albumCatalog1.setAlbum(album);
        albumCatalogDaoTest.update(albumCatalog1);
        assertEquals(albumCatalogDaoTest.findAll().get(0), albumCatalog1);
    }

    @Test
    public void getAlbumsFromCatalogTest() throws AlbumDaoException {
        Album album1 = new Album("album1");
        Album album2 = new Album("album2");
        Album album3 = new Album("album3");
        Catalog catalog = new Catalog("catalog1");
        AlbumCatalog albumCatalog1 = new AlbumCatalog(album1, catalog);
        AlbumCatalog albumCatalog2 = new AlbumCatalog(album2, catalog);
        AlbumCatalog albumCatalog3 = new AlbumCatalog(album3, catalog);
        albumCatalogDaoTest.create(albumCatalog1);
        albumCatalogDaoTest.create(albumCatalog2);
        albumCatalogDaoTest.create(albumCatalog3);
        List<Album> expected = new ArrayList<Album>();
        expected.add(album1);
        expected.add(album2);
        expected.add(album3);
        List<Album> albumList = albumCatalogDaoTest.getAlbumsFromCatalog(catalog);
        assertEquals(albumList, expected);
    }

    @Test(expected = AlbumDaoException.class)
    public void getAlbumsFromEmptyCatalog() throws AlbumDaoException {
        Catalog catalog = new Catalog("catalog1");
        AlbumCatalog albumCatalog1 = new AlbumCatalog(null, catalog);
        albumCatalogDaoTest.create(albumCatalog1);
        albumCatalogDaoTest.getAlbumsFromCatalog(catalog);
    }
}
