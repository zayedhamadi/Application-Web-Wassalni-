package covoiturage.covoiturage.Service;


import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Publication;
import covoiturage.covoiturage.Entity.Reserve;
import covoiturage.covoiturage.Entity.dto.ReserverDto.ReserveDto;
import covoiturage.covoiturage.Repository.ConductorRepository;
import covoiturage.covoiturage.Repository.PublicationRepository;
import covoiturage.covoiturage.Repository.ReserveRepository;
import covoiturage.covoiturage.Service.Service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {

    private final ReserveRepository reserveRepository;
    private final ConductorRepository conductorRepository;

    private final PublicationRepository publicationRepository;

    @Override
    public Reserve ReserveCovoiturage(ReserveDto v) {
        Conductor conductor = this.conductorRepository.findById(v.getIdConductor()).orElseThrow(() -> new RuntimeException("Conductor not found"));
        Publication publication = this.publicationRepository.findById((long) v.getIdpub()).orElseThrow(() -> new RuntimeException("publication not found"));
        Reserve reserve = Reserve.builder().conductor(conductor).publication(publication).build();
        System.out.println(conductor + "  est cree  :" + reserve);
        return this.reserveRepository.save(reserve);
    }

    @Override
    public ResponseEntity<String> deleteReserver(Integer id) {

        try {
            Optional<Reserve> reserve = this.reserveRepository.findById(id);
            if (reserve.isPresent()) {
                this.reserveRepository.delete(reserve.get());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Reserve  deleted successfully!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Reserve: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reserve> getAllReserve() {
        try {
            return new ArrayList<>(this.reserveRepository.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return Collections.emptyList();

    }

    @Override
    public ResponseEntity<Reserve> getSpecificReserve(Integer id) {
        Optional<Reserve> optionalReserve = this.reserveRepository.findById(id);
        return optionalReserve.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
