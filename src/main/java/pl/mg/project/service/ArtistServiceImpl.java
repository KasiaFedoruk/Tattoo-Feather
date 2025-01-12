package pl.mg.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mg.project.dao.ArtistRepository;
import pl.mg.project.entity.Artist;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService{

    private ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(int id) {
        Optional<Artist> result = artistRepository.findById(id);
        Artist artist = null;
        if (result.isPresent()) {
            artist = result.get();
        } else {
            throw new RuntimeException("Nie ma takiego id " + id);
        }
        return artist;
    }

    @Override
    @Transactional
    public void save(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    @Transactional
    public void delete(String name) {
        Optional<Artist> artist = artistRepository.findByName(name);

        if (artist.isPresent()) {
            artistRepository.delete(artist.get());
        } else {
            throw new RuntimeException("Nie znaleziono artysty o nazwie: " + name);
        }
    }
}
