package org.ds.webalbum.model;

import javax.persistence.*;


@Entity
@Table(name = "photoalbum")
public class PhotoAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photoId")
    private Photo photo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "albumId")
    private Album album;

    public PhotoAlbum(Photo photo, Album album) {
        this.photo = photo;
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
