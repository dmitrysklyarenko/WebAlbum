package org.ds.webalbum.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "link")
    private String link;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "photo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<PhotoAlbum> photoAlbumList;

    public Photo(String caption, String link) {
        this.caption = caption;
        this.link = link;
    }

    public Photo() {}

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<PhotoAlbum> getPhotoAlbumList() {
        return photoAlbumList;
    }

    public void setPhotoAlbumList(List<PhotoAlbum> photoAlbumList) {
        this.photoAlbumList = photoAlbumList;
    }
}
