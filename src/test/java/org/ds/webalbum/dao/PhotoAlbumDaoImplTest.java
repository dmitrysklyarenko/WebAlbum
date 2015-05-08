package org.ds.webalbum.dao;

import org.ds.webalbum.model.Album;
import org.ds.webalbum.model.Photo;
import org.ds.webalbum.model.PhotoAlbum;
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
public class PhotoAlbumDaoImplTest {

    @Autowired
    private PhotoAlbumDao photoAlbumDaoTest;

    @Test
    public void createPhotoAlbumTest() {
        Photo photo = new Photo("photo1", "link");
        Album album = new Album("album1");
        PhotoAlbum photoAlbum = new PhotoAlbum(photo, album);
        photoAlbumDaoTest.create(photoAlbum);
        assertEquals(photoAlbumDaoTest.findAll().get(0), photoAlbum);
    }

    @Test
    public void getListOfPhotoAlbumTest() {
        Photo photo1 = new Photo("ph1", "link1");
        Photo photo2 = new Photo("ph2", "link2");
        Album album1 = new Album("album1");
        Album album2 = new Album("album2");
        PhotoAlbum photoAlbum1 = new PhotoAlbum(photo1, album1);
        PhotoAlbum photoAlbum2 = new PhotoAlbum(photo2, album2);
        List<PhotoAlbum> photoAlbumList = new ArrayList<PhotoAlbum>();
        photoAlbumList.add(photoAlbum1);
        photoAlbumList.add(photoAlbum2);
        photoAlbumDaoTest.create(photoAlbum1);
        photoAlbumDaoTest.create(photoAlbum2);
        assertEquals(photoAlbumList, photoAlbumDaoTest.findAll());
    }

    @Test
    public void listOfPhotoAlbumIsNotEmpty() {
        Photo photo1 = new Photo("ph1", "link1");
        Photo photo2 = new Photo("ph2", "link2");
        Photo photo3 = new Photo("ph3", "link3");
        Album album1 = new Album("album1");
        Album album2 = new Album("album2");
        Album album3 = new Album("album3");
        PhotoAlbum photoAlbum1 = new PhotoAlbum(photo1, album1);
        PhotoAlbum photoAlbum2 = new PhotoAlbum(photo2, album2);
        PhotoAlbum photoAlbum3 = new PhotoAlbum(photo3, album3);
        photoAlbumDaoTest.create(photoAlbum1);
        photoAlbumDaoTest.create(photoAlbum2);
        photoAlbumDaoTest.create(photoAlbum3);
        assertEquals(photoAlbumDaoTest.findAll().size(), 3);
    }

    @Test
    public void listOfPhotoAlbumIsEmpty() {
        assertEquals(photoAlbumDaoTest.findAll().size(), 0);
    }

    @Test
    public void getOnePhotoAlbumTest() throws DaoException {
        Photo photo1 = new Photo("ph1", "link1");
        Photo photo2 = new Photo("ph2", "link2");
        Photo photo3 = new Photo("ph3", "link3");
        Album album1 = new Album("album1");
        Album album2 = new Album("album2");
        Album album3 = new Album("album3");
        PhotoAlbum photoAlbum1 = new PhotoAlbum(photo1, album1);
        PhotoAlbum photoAlbum2 = new PhotoAlbum(photo2, album2);
        PhotoAlbum photoAlbum3 = new PhotoAlbum(photo3, album3);
        List<PhotoAlbum> photoAlbumList = new ArrayList<PhotoAlbum>();
        photoAlbumList.add(photoAlbum1);
        photoAlbumList.add(photoAlbum2);
        photoAlbumList.add(photoAlbum3);
        photoAlbumDaoTest.create(photoAlbum1);
        photoAlbumDaoTest.create(photoAlbum2);
        photoAlbumDaoTest.create(photoAlbum3);
        assertEquals(photoAlbumList.get(1), photoAlbumDaoTest.findOne(2));
    }

    @Test(expected = DaoException.class)
    public void getNotExistingPhotoAlbumTest() throws DaoException {
        photoAlbumDaoTest.findOne(1);
    }

    @Test
    public void deletePhotoAlbumTest() {
        Photo photo = new Photo("photo1", "link");
        Album album = new Album("album1");
        PhotoAlbum photoAlbum1 = new PhotoAlbum(photo, album);
        photoAlbumDaoTest.create(photoAlbum1);
        photoAlbumDaoTest.delete(photoAlbum1);
        assertEquals(photoAlbumDaoTest.findAll().size(), 0);
    }

    @Test
    public void updatePhotoAlbumTest() {
        Photo photo = new Photo("photo1", "link");
        Album album = new Album("album1");
        PhotoAlbum photoAlbum1 = new PhotoAlbum(photo, album);
        photoAlbumDaoTest.create(photoAlbum1);
        photo.setCaption("photoTest");
        photo.setLink("linkTest");
        album.setCaption("testAlbum");
        photoAlbum1.setAlbum(album);
        photoAlbum1.setPhoto(photo);
        photoAlbumDaoTest.update(photoAlbum1);
        assertEquals(photoAlbumDaoTest.findAll().get(0), photoAlbum1);
    }

    @Test
    public void getPhotosFromAlbumTest() throws PhotoDaoException {
        Photo photo1 = new Photo("ph1", "link1");
        Photo photo2 = new Photo("ph2", "link2");
        Photo photo3 = new Photo("ph3", "link3");
        Album album = new Album("album1");
        PhotoAlbum photoAlbum1 = new PhotoAlbum(photo1, album);
        PhotoAlbum photoAlbum2 = new PhotoAlbum(photo2, album);
        PhotoAlbum photoAlbum3 = new PhotoAlbum(photo3, album);
        photoAlbumDaoTest.create(photoAlbum1);
        photoAlbumDaoTest.create(photoAlbum2);
        photoAlbumDaoTest.create(photoAlbum3);
        List<Photo> expected = new ArrayList<Photo>();
        expected.add(photo1);
        expected.add(photo2);
        expected.add(photo3);
        List<Photo> photoList = photoAlbumDaoTest.getPhotosByAlbum(album);
        assertEquals(photoList, expected);
    }

    @Test(expected = PhotoDaoException.class)
    public void getPhotosFromEmptyAlbum() throws PhotoDaoException {
        Album album = new Album("album1");
        PhotoAlbum photoAlbum1 = new PhotoAlbum(null, album);
        photoAlbumDaoTest.create(photoAlbum1);
        photoAlbumDaoTest.getPhotosByAlbum(album);
    }

}
