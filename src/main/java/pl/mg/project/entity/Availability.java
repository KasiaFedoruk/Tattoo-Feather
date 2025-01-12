package pl.mg.project.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_artist")
    private Artist artist;

    @Override
    public String toString() {
        return "Availability{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", artist=" + artist +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Availability(String date, String time, Artist artist) {
        this.date = date;
        this.time = time;
        this.artist = artist;
    }

    public Availability() {
    }
}
