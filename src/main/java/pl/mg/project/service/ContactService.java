package pl.mg.project.service;

import org.springframework.stereotype.Service;
import pl.mg.project.entity.Contact;

import java.util.List;


public interface ContactService {

    List<Contact> findAll();
    void save(Contact contact);
    Contact findContactById(int id);
    void delete(int id);
}
