package org.ds.webalbum.service;

import org.ds.webalbum.dao.CatalogDao;
import org.ds.webalbum.dao.DaoException;
import org.ds.webalbum.model.Catalog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*/testContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CatalogServiceImplTest {

    @Mock
    private CatalogDao catalogDao;

    private CatalogServiceImpl catalogServiceImpl;

    @Before
    public void initBefore() {
        MockitoAnnotations.initMocks(this);
        catalogServiceImpl = new CatalogServiceImpl();
        catalogServiceImpl.setCatalogDao(catalogDao);
    }

    @Test
    public void addCatalogTest() {
        Catalog catalog = new Catalog("catalog1");
        catalogServiceImpl.addCatalog(catalog);
        verify(catalogDao, times(1)).create(catalog);
    }

    @Test
    public void updateCatalogTest() {
        Catalog catalog = new Catalog("catalog1");
        catalogServiceImpl.updateCatalog(catalog);
        verify(catalogDao, times(1)).update(catalog);
    }

    @Test
    public void getCatalogTest() throws DaoException {
        Catalog catalog = new Catalog("catalog");
        when(catalogDao.findOne(anyInt())).thenReturn(catalog);
        Catalog result = catalogServiceImpl.getCatalog(1);
        verify(catalogDao, times(1)).findOne(1);
        assertEquals(catalog, result);
    }

    @Test(expected = DaoException.class)
    public void getNotExistingCatalog() throws DaoException {
        when(catalogDao.findOne(anyInt())).thenThrow(DaoException.class);
        Catalog result = catalogServiceImpl.getCatalog(1);
        verify(catalogDao, times(1)).findOne(1);
    }

    @Test
    public void deleteCatalogTest() {
        Catalog catalog = new Catalog("catalog");
        catalogServiceImpl.deleteCatalog(catalog);
        verify(catalogDao, times(1)).delete(catalog);
    }
}
