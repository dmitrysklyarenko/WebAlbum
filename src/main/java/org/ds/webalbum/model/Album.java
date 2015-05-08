package org.ds.webalbum.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "caption")
    private String caption;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<PhotoAlbum> photoAlbumList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<AlbumCatalog> albumCatalogList;

    public Album(String caption) {
        this.caption = caption;
    }

    public Album() {}

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

    public List<PhotoAlbum> getPhotoAlbumList() {
        return photoAlbumList;
    }

    public void setPhotoAlbumList(List<PhotoAlbum> photoAlbumList) {
        this.photoAlbumList = photoAlbumList;
    }

    public List<AlbumCatalog> getAlbumCatalogList() {
        return albumCatalogList;
    }

    public void setAlbumCatalogList(List<AlbumCatalog> albumCatalogList) {
        this.albumCatalogList = albumCatalogList;
    }
}
