package org.ds.webalbum.service;

import org.ds.webalbum.dao.DaoException;
import org.ds.webalbum.dao.PhotoAlbumDao;
import org.ds.webalbum.dao.PhotoDao;
import org.ds.webalbum.dao.PhotoDaoException;
import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.Photo;
import org.ds.webalbum.model.PhotoAlbum;
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
public class PhotoServiceImplTest {


    private PhotoServiceImpl photoServiceImpl;
    @Mock
    private PhotoDao photoDao;
    @Mock
    private PhotoAlbumDao photoAlbumDao;

    @Before
    public void initBefore() {
        MockitoAnnotations.initMocks(this);
        photoServiceImpl = new PhotoServiceImpl();
        photoServiceImpl.setPhotoDao(photoDao);
        photoServiceImpl.setPhotoAlbumDao(photoAlbumDao);
    }

    @Test
    public void addPhotoTest() {
        Photo photo = new Photo("photo1", "link1");
        photoServiceImpl.addPhoto(photo);
        verify(photoDao, times(1)).create(photo);
    }

    @Test
    public void updatePhotoTest() {
        Photo photo = new Photo("photo1", "link1");
        photoServiceImpl.updatePhoto(photo);
        verify(photoDao, times(1)).update(photo);
    }

    @Test
    public void getPhotoTest() throws DaoException {
        Photo photo = new Photo("photo", "link");
        when(photoDao.findOne(anyInt())).thenReturn(photo);
        Photo result = photoServiceImpl.getPhoto(1);
        verify(photoDao, times(1)).findOne(1);
        assertEquals(photo, result);
    }

    @Test(expected = DaoException.class)
    public void getNotExistingPhoto() throws DaoException {
        when(photoDao.findOne(anyInt())).thenThrow(DaoException.class);
        Photo result = photoServiceImpl.getPhoto(1);
        verify(photoDao, times(1)).findOne(1);
    }

    @Test
    public void getPhotosFromAlbumTest() throws PhotoDaoException {
        Photo photo1 = new Photo("photo1", "link1");
        Photo photo2 = new Photo("photo2", "link2");
        List<Photo> expected = new ArrayList<Photo>();
        expected.add(photo1);
        expected.add(photo2);
        when(photoAlbumDao.getPhotosByAlbum(any(Album.class))).thenReturn(expected);
        Album album = new Album("album");
        List<Photo> result = photoServiceImpl.getPhotosFromAlbum(album);
        verify(photoAlbumDao, times(1)).getPhotosByAlbum(album);
        assertEquals(expected, result);
    }

    @Test(expected = PhotoDaoException.class)
    public void getPhotosFromEmptyAlbum() throws PhotoDaoException {
        when(photoAlbumDao.getPhotosByAlbum(any(Album.class))).thenThrow(PhotoDaoException.class);
        Album album = new Album("album");
        photoServiceImpl.getPhotosFromAlbum(album);
        verify(photoAlbumDao, times(1)).getPhotosByAlbum(album);
    }

    @Test
    public void putPhotoToAlbumTest() {
        Photo photo = new Photo("photo", "link");
        Album album = new Album("album");
        photoServiceImpl.putPhotoToAlbum(photo, album);
        verify(photoAlbumDao, times(1)).create(any(PhotoAlbum.class));
    }

    @Test
    public void removePhotoFromAlbumTest() {
        Photo photo = new Photo("photo", "link");
        Album album = new Album("album");
        photoServiceImpl.removePhotoFromAlbum(photo, album);
        verify(photoAlbumDao, times(1)).delete(any(PhotoAlbum.class));
    }

    @Test
    public void deletePhotoTest() {
        Photo photo = new Photo("photo", "link");
        photoServiceImpl.deletePhoto(photo);
        verify(photoDao, times(1)).delete(photo);
    }
}
