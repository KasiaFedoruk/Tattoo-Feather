package pl.mg.project.service;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.project.dao.ContactRepository;
import pl.mg.project.entity.Contact;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public Contact findContactById(int id) {
        Optional<Contact> result = contactRepository.findById(id);
        Contact theContact = null;

        if (result.isPresent()) {
            theContact = result.get();
        } else {
            throw new RuntimeException("Nie ma takiego id: " + id);
        }
        return theContact;
    }

    @Override
    @Transactional
    public void delete(int id) {
        contactRepository.deleteById(id);
    }
}
