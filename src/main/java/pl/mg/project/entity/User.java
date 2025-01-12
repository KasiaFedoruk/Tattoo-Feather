package pl.mg.project.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Positive(message = "Nie poprawna wartość telefonu")
    @Column(name = "phone_number")
    private int phoneNumber;

    @NotBlank(message = "Nazwa użytkownika jest wymagana!")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Hasło jest wymagane!")
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Appointment> appointments;

    public User() {
    }

    public User(int phoneNumber, String username, String password) {
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;

    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
