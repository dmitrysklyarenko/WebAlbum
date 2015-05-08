package org.ds.webalbum.model;

import javax.persistence.*;


@Entity
@Table(name = "albumcatalog")
public class AlbumCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "albumId")
    private Album album;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalogId")
    private Catalog catalog;

    public AlbumCatalog(Album album, Catalog catalog) {
        this.album = album;
        this.catalog = catalog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
