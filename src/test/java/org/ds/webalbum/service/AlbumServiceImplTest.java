package org.ds.webalbum.service;

import org.ds.webalbum.dao.AlbumCatalogDao;
import org.ds.webalbum.dao.AlbumDao;
import org.ds.webalbum.dao.AlbumDaoException;
import org.ds.webalbum.dao.DaoException;
import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.AlbumCatalog;
import org.ds.webalbum.model.Catalog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*/testContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AlbumServiceImplTest {

    private AlbumServiceImpl albumServiceImpl;
    @Mock
    private AlbumDao albumDao;
    @Mock
    private AlbumCatalogDao albumCatalogDao;

    @Before
    public void initBefore() {
        MockitoAnnotations.initMocks(this);
        albumServiceImpl = new AlbumServiceImpl();
        albumServiceImpl.setAlbumDao(albumDao);
        albumServiceImpl.setAlbumCatalogDao(albumCatalogDao);
    }

    @Test
    public void addAlbumTest() {
        Album album = new Album("album1");
        albumServiceImpl.addAlbum(album);
        verify(albumDao, times(1)).create(album);
    }

    @Test
    public void updateAlbumTest() {
        Album album = new Album("album1");
        albumServiceImpl.updateAlbum(album);
        verify(albumDao, times(1)).update(album);
    }

    @Test
    public void getAlbumTest() throws DaoException {
        Album album = new Album("album");
        when(albumDao.findOne(anyInt())).thenReturn(album);
        Album result = albumServiceImpl.getAlbum(1);
        verify(albumDao, times(1)).findOne(1);
        assertEquals(album, result);
    }

    @Test(expected = DaoException.class)
    public void getNotExistingAlbum() throws DaoException {
        when(albumDao.findOne(anyInt())).thenThrow(DaoException.class);
        Album result = albumServiceImpl.getAlbum(1);
        verify(albumDao, times(1)).findOne(1);
    }

    @Test
    public void getAlbumsFromCatalogTest() throws AlbumDaoException {
        Album album1 = new Album("album1");
        Album album2 = new Album("album2");
        List<Album> expected = new ArrayList<Album>();
        expected.add(album1);
        expected.add(album2);
        when(albumCatalogDao.getAlbumsFromCatalog(any(Catalog.class))).thenReturn(expected);
        Catalog catalog = new Catalog("catalog");
        List<Album> result = albumServiceImpl.getAlbumsFromCatalog(catalog);
        verify(albumCatalogDao, times(1)).getAlbumsFromCatalog(catalog);
        assertEquals(expected, result);
    }

    @Test(expected = AlbumDaoException.class)
    public void getAlbumsFromEmptyCatalog() throws AlbumDaoException {
        when(albumCatalogDao.getAlbumsFromCatalog(any(Catalog.class))).thenThrow(AlbumDaoException.class);
        Catalog catalog = new Catalog("catalog");
        albumServiceImpl.getAlbumsFromCatalog(catalog);
        verify(albumCatalogDao, times(1)).getAlbumsFromCatalog(catalog);
    }

    @Test
    public void putAlbumToCatalogTest() {
        Album album = new Album("album");
        Catalog catalog = new Catalog("catalog");
        albumServiceImpl.putAlbumToCatalog(album, catalog);
        verify(albumCatalogDao, times(1)).create(any(AlbumCatalog.class));
    }

    @Test
    public void removeAlbumFromCatalogTest() {
        Album album = new Album("album");
        Catalog catalog = new Catalog("catalog");
        albumServiceImpl.removeAlbumFromCatalog(album, catalog);
        verify(albumCatalogDao, times(1)).delete(any(AlbumCatalog.class));
    }

    @Test
    public void deleteAlbumTest() {
        Album album = new Album("album");
        albumServiceImpl.deleteAlbum(album);
        verify(albumDao, times(1)).delete(album);
    }
}
