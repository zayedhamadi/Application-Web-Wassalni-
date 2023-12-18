    package covoiturage.covoiturage.Repository;

    import covoiturage.covoiturage.Entity.Conductor;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;

    @Repository
    public interface ConductorRepository extends JpaRepository<Conductor, Integer> {
        Conductor getByEmail(String email);

        Optional<Conductor> findFirstByEmail(String email);
    }
