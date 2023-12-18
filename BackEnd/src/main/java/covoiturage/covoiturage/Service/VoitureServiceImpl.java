package covoiturage.covoiturage.Service;


import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Entity.dto.VoitureDTO;
import covoiturage.covoiturage.Service.Service.VoitureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.function.Consumer;

import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Repository.ConductorRepository;
import covoiturage.covoiturage.Repository.VoitureRepository;

@Service
@AllArgsConstructor
public class VoitureServiceImpl implements VoitureService {
    private final VoitureRepository voitureRepository;
    private final ConductorRepository conductorRepository;

    public Voiture saveVoiture(VoitureDTO v) {


        Conductor conductor = conductorRepository.findById(v.getIdConductor())
                .orElseThrow(() -> new RuntimeException("Conductor not found"));


        Voiture voiture = Voiture.builder()
                .conductor(conductor)
                .marqueVoiture(v.getMarqueVoiture())
                .descriptionVoiture(v.getDescriptionVoiture())
                .build();
        System.out.println(voiture + "cree par" + conductor.getIdConductor());
        return this.voitureRepository.save(voiture);
    }

    public List<Voiture> getAllVoitureOfUser() {
        return null;
    }


    private void updateFieldIfValid(String updatedValue, Consumer<String> fieldSetter) {
        if (updatedValue != null && !updatedValue.trim().isEmpty()) {
            fieldSetter.accept(updatedValue.trim());
        }
    }

    @Override
    public Voiture updateVoiture(VoitureDTO dto) {
        try {
            Optional<Conductor> optionalConductor = this.conductorRepository.findById(dto.getIdConductor());

            if (optionalConductor.isPresent()) {
                Optional<Voiture> optionalVoiture = this.voitureRepository.findById(dto.getIdVoiture());

                if (optionalVoiture.isPresent()) {
                    Voiture existingVoiture = optionalVoiture.get();

                    if (existingVoiture.getConductor().getIdConductor() == dto.getIdConductor()) {
                        updateFieldIfValid(dto.getMarqueVoiture(), existingVoiture::setMarqueVoiture);
                        updateFieldIfValid(dto.getDescriptionVoiture(), existingVoiture::setDescriptionVoiture);

                        return voitureRepository.save(existingVoiture);
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }

        return null;
    }

    @Override
    public ResponseEntity<String> deleteVoiture(Integer id) {
        try {
            Optional<Voiture> optionalVoiture = voitureRepository.findById(id);

            if (optionalVoiture.isPresent()) {
                Voiture voiture = optionalVoiture.get();
                voitureRepository.delete(voiture);
                return ResponseEntity.ok("Voiture deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<Voiture> getAllVoiture() {
        try {
            return new ArrayList<>(this.voitureRepository.findAll());
        } catch (Exception e) {
            System.out.println( e.getMessage());

        }
        return Collections.emptyList();

    }

    public List<String> getCarNamesForUser(int conductorId) {
        List<String> carNames = new ArrayList<>();

        try {
            Optional<Conductor> conductorOptional = conductorRepository.findById(conductorId);
            if (conductorOptional.isPresent()) {
                Conductor conductor = conductorOptional.get();
                List<Voiture> voitures = voitureRepository.findByConductor(conductor);

                for (Voiture voiture : voitures) {
                    carNames.add(voiture.getMarqueVoiture());
                }
            } else {
                throw new RuntimeException("Conductor not found with ID: " + conductorId);
            }
        } catch (Exception e) {
            System.out.println("Error fetching car names for user: " + e.getMessage());
        }

        return carNames;
    }

}
