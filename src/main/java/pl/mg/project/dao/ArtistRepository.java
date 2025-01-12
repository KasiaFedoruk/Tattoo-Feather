package pl.mg.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mg.project.entity.Artist;


import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByName(String name);

}
