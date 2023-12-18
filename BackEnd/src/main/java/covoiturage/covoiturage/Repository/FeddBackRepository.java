package covoiturage.covoiturage.Repository;

import covoiturage.covoiturage.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeddBackRepository  extends JpaRepository<Feedback, Integer> {
}
