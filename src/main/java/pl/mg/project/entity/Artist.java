package pl.mg.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo_URL")
    private String photoURL;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "artist",
                cascade = CascadeType.ALL)
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy = "artist",
                cascade = CascadeType.ALL)
    private List<Availability> availabilities;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Appointment> appointments;


    public Artist() {
    }

    public Artist(String name, String photoURL, String description, List<Portfolio> portfolios, List<Availability> availabilities, List<Appointment> appointments) {
        this.name = name;
        this.photoURL = photoURL;
        this.description = description;
        this.portfolios = portfolios;
        this.availabilities = availabilities;
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoURL='" + photoURL + '\'' +
                ", description='" + description + '\'' +
                ", portfolios=" + portfolios +
                ", availabilities=" + availabilities +
                ", appointments=" + appointments +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
