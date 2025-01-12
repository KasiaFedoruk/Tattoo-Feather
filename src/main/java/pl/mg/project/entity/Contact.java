package pl.mg.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "message")
    private String message;

    public Contact() {
    }

    public Contact(String phone, String message) {
        this.phone = phone;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
