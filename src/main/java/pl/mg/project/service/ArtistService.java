package pl.mg.project.service;

import org.springframework.stereotype.Service;
import pl.mg.project.dao.ArtistRepository;
import pl.mg.project.entity.Artist;

import java.util.List;


public interface ArtistService {


    List<Artist> findAll();
    Artist findById(int id);
    void save(Artist artist);
    void delete(String name);
}
