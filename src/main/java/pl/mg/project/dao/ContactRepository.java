package pl.mg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mg.project.entity.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
