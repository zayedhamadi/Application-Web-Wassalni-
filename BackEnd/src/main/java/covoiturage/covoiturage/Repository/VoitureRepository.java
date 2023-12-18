package covoiturage.covoiturage.Repository;

import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Integer> {
    List<Voiture> findByConductor(Conductor conductor);
}