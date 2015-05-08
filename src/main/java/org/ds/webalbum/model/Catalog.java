package org.ds.webalbum.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "catalogs")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "caption")
    private String caption;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalog", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<AlbumCatalog> albumCatalogList;

    public Catalog(String caption) {
        this.caption = caption;
    }

    public Catalog() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<AlbumCatalog> getAlbumCatalogList() {
        return albumCatalogList;
    }

    public void setAlbumCatalogList(List<AlbumCatalog> albumCatalogList) {
        this.albumCatalogList = albumCatalogList;
    }
}
