package pl.mg.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "photo_URL")
    private String photoURL;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public Portfolio(String photoURL, Artist artist) {
        this.photoURL = photoURL;
        this.artist = artist;
    }

    public Portfolio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", photoURL='" + photoURL + '\'' +
                ", artist=" + artist +
                '}';
    }
}
