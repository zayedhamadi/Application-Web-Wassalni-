package covoiturage.covoiturage.Repository;

import covoiturage.covoiturage.Entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
}
