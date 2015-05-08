package org.ds.webalbum.dao;

import static org.junit.Assert.*;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*/testContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CatalogDaoImplTest {

    @Autowired
    private CatalogDao catalogDaoTest;

    @Test
    public void createCatalogTest() {
        Catalog catalog = new Catalog("catalog1");
        catalogDaoTest.create(catalog);
        assertEquals(catalogDaoTest.findAll().get(0), catalog);
    }

    @Test
    public void getListOfCatalogsTest() {
        Catalog catalog1 = new Catalog("cat1");
        Catalog catalog2 = new Catalog("cat2");
        List<Catalog> catalogList = new ArrayList<Catalog>();
        catalogList.add(catalog1);
        catalogList.add(catalog2);
        catalogDaoTest.create(catalog1);
        catalogDaoTest.create(catalog2);
        assertEquals(catalogList, catalogDaoTest.findAll());
    }

    @Test
    public void listOfCatalogsIsNotEmpty() {
        Catalog catalog1 = new Catalog("cat1");
        Catalog catalog2 = new Catalog("cat2");
        Catalog catalog3 = new Catalog("cat3");
        catalogDaoTest.create(catalog1);
        catalogDaoTest.create(catalog2);
        catalogDaoTest.create(catalog3);
        assertEquals(catalogDaoTest.findAll().size(), 3);
    }

    @Test
    public void listOfCatalogsIsEmpty() {
        assertEquals(catalogDaoTest.findAll().size(), 0);
    }

    @Test
    public void getOneCatalogTest() throws DaoException {
        Catalog catalog1 = new Catalog("cat1");
        Catalog catalog2 = new Catalog("cat2");
        Catalog catalog3 = new Catalog("cat3");
        List<Catalog> catalogList = new ArrayList<Catalog>();
        catalogList.add(catalog1);
        catalogList.add(catalog2);
        catalogList.add(catalog3);
        catalogDaoTest.create(catalog1);
        catalogDaoTest.create(catalog2);
        catalogDaoTest.create(catalog3);
        assertEquals(catalogList.get(1), catalogDaoTest.findOne(2));
    }

    @Test(expected = DaoException.class)
    public void getNotExistingCatalogTest() throws DaoException {
        catalogDaoTest.findOne(1);
    }

    @Test
    public void deleteCatalogTest() {
        Catalog catalog = new Catalog("catalog1");
        catalogDaoTest.create(catalog);
        catalogDaoTest.delete(catalog);
        assertEquals(catalogDaoTest.findAll().size(), 0);
    }

    @Test
    public void updateCatalogTest() {
        Catalog catalog = new Catalog("catalog1");
        catalogDaoTest.create(catalog);
        catalog.setCaption("catalogTest");
        catalogDaoTest.update(catalog);
        assertEquals(catalogDaoTest.findAll().get(0), catalog);
    }
}
