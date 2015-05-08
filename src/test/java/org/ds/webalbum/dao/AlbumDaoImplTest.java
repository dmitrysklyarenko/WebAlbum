package org.ds.webalbum.dao;

import static org.junit.Assert.*;

import org.ds.webalbum.model.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*/testContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class AlbumDaoImplTest {

    @Autowired
    private AlbumDao albumDaoTest;

    @Test
    public void createAlbumTest() {
        Album album = new Album("album1");
        albumDaoTest.create(album);
        assertEquals(albumDaoTest.findAll().get(0), album);
    }

    @Test
    public void getListOfAlbumsTest() {
        Album album1 = new Album("al1");
        Album album2 = new Album("al2");
        List<Album> albumList = new ArrayList<Album>();
        albumList.add(album1);
        albumList.add(album2);
        albumDaoTest.create(album1);
        albumDaoTest.create(album2);
        assertEquals(albumList, albumDaoTest.findAll());
    }

    @Test
    public void listOfAlbumsIsNotEmpty() {
        Album album1 = new Album("al1");
        Album album2 = new Album("al2");
        Album album3 = new Album("al3");
        albumDaoTest.create(album1);
        albumDaoTest.create(album2);
        albumDaoTest.create(album3);
        assertEquals(albumDaoTest.findAll().size(), 3);
    }

    @Test
    public void listOfAlbumsIsEmpty() {
        assertEquals(albumDaoTest.findAll().size(), 0);
    }

    @Test
    public void getOneAlbumTest() throws DaoException {
        Album album1 = new Album("al1");
        Album album2 = new Album("al2");
        Album album3 = new Album("al3");
        List<Album> albumList = new ArrayList<Album>();
        albumList.add(album1);
        albumList.add(album2);
        albumList.add(album3);
        albumDaoTest.create(album1);
        albumDaoTest.create(album2);
        albumDaoTest.create(album3);
        assertEquals(albumList.get(1), albumDaoTest.findOne(2));
    }

    @Test(expected = DaoException.class)
    public void getNotExistingAlbumTest() throws DaoException {
        albumDaoTest.findOne(1);
    }

    @Test
    public void deleteAlbumTest() {
        Album album = new Album("album1");
        albumDaoTest.create(album);
        albumDaoTest.delete(album);
        assertEquals(albumDaoTest.findAll().size(), 0);
    }

    @Test
    public void updateAlbumTest() {
        Album album = new Album("album1");
        albumDaoTest.create(album);
        album.setCaption("albumTest");
        albumDaoTest.update(album);
        assertEquals(albumDaoTest.findAll().get(0), album);
    }
}
