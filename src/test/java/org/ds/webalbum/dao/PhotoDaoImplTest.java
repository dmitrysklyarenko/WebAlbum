package org.ds.webalbum.dao;

import static org.junit.Assert.*;

import org.ds.webalbum.model.Photo;
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
public class PhotoDaoImplTest {

    @Autowired
    private PhotoDao photoDaoTest;

    @Test
    public void createPhotoTest() {
        Photo photo = new Photo("photo1", "link");
        photoDaoTest.create(photo);
        assertEquals(photoDaoTest.findAll().get(0), photo);
    }

    @Test
    public void getListOfPhotosTest() {
        Photo photo1 = new Photo("ph1", "link1");
        Photo photo2 = new Photo("ph2", "link2");
        List<Photo> photoList = new ArrayList<Photo>();
        photoList.add(photo1);
        photoList.add(photo2);
        photoDaoTest.create(photo1);
        photoDaoTest.create(photo2);
        assertEquals(photoList, photoDaoTest.findAll());
    }

    @Test
    public void listOfPhotosIsNotEmpty() {
        Photo photo1 = new Photo("ph1", "link1");
        Photo photo2 = new Photo("ph2", "link2");
        Photo photo3 = new Photo("ph3", "link3");
        photoDaoTest.create(photo1);
        photoDaoTest.create(photo2);
        photoDaoTest.create(photo3);
        assertEquals(photoDaoTest.findAll().size(), 3);
    }

    @Test
    public void listOfPhotosIsEmpty() {
        assertEquals(photoDaoTest.findAll().size(), 0);
    }

    @Test
    public void getOnePhotoTest() throws DaoException {
        Photo photo1 = new Photo("ph1", "link1");
        Photo photo2 = new Photo("ph2", "link2");
        Photo photo3 = new Photo("ph3", "link3");
        List<Photo> photoList = new ArrayList<Photo>();
        photoList.add(photo1);
        photoList.add(photo2);
        photoList.add(photo3);
        photoDaoTest.create(photo1);
        photoDaoTest.create(photo2);
        photoDaoTest.create(photo3);
        assertEquals(photoList.get(1), photoDaoTest.findOne(2));
    }

    @Test(expected = DaoException.class)
    public void getNotExistingPhotoTest() throws DaoException {
        photoDaoTest.findOne(1);
    }

    @Test
    public void deletePhotoTest() {
        Photo photo = new Photo("photo1", "link");
        photoDaoTest.create(photo);
        photoDaoTest.delete(photo);
        assertEquals(photoDaoTest.findAll().size(), 0);
    }

    @Test
    public void updatePhotoTest() {
        Photo photo = new Photo("photo1", "link");
        photoDaoTest.create(photo);
        photo.setCaption("photoTest");
        photo.setLink("linkTest");
        photoDaoTest.update(photo);
        assertEquals(photoDaoTest.findAll().get(0), photo);
    }
}
