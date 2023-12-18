package covoiturage.covoiturage.Controller;


import covoiturage.covoiturage.Entity.Publication;
import covoiturage.covoiturage.Entity.Reserve;
import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Entity.dto.PublicationDto;
import covoiturage.covoiturage.Entity.dto.ReserverDto.ReserveDto;
import covoiturage.covoiturage.Entity.dto.VoitureDTO;
import covoiturage.covoiturage.Entity.dto.UserDto.*;
import covoiturage.covoiturage.Exception.ExceptionConductor;
import covoiturage.covoiturage.Repository.PublicationRepository;
import covoiturage.covoiturage.Repository.VoitureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Repository.ConductorRepository;
import covoiturage.covoiturage.Service.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/user/"})
@CrossOrigin(origins = {"http://localhost:4200"})
public class ConducteurController {
    private final VoitureRepository voitureRepository;
    private final ConductorRepository conductorRepository;
    private final ConductorServiceImpl conductorService;
    private final PublicationServiceImpl publicationService;
    private final VoitureServiceImpl voitureService;
    private final ReserveServiceImpl reserveService;

    private final PublicationRepository publicationRepository;

    @PutMapping({"updateConductor"})
    public ResponseEntity<Conductor> updateUser(@RequestBody EditProfileDto editProfileDto) {
        try {
            Conductor updatedConductor = this.conductorService.updateConductor(editProfileDto);
            return updatedConductor != null ? ResponseEntity.ok(updatedConductor) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception var3) {
            System.out.println(ExceptionConductor.Error_UpdateUser);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(
            path = {"deleteCustomer/{ID}"}
    )
    public ResponseEntity<String> deleteUser(@PathVariable Integer ID) {
        try {
            return this.conductorService.deleteCustomer(ID);
        } catch (Exception var3) {
            System.out.println(ExceptionConductor.Error_DeleteUser);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = {"specificConductor/{ID}"})
    public ResponseEntity<Conductor> specificConductor(@PathVariable Integer ID) {
        return this.conductorService.SpecificConductor(ID);
    }

    @PostMapping({"publier"})
    public ResponseEntity<?> ajouterPublication(@RequestBody PublicationDto dto) {
        System.out.println(dto);
        if (dto != null) {
            this.publicationService.savePublication(dto);
            return ResponseEntity.ok("{ \"message\": \"Publié avec succès\" }");
        } else {
            return null;
        }
    }


    @GetMapping({"/carNames/{conductorId}"})
    public ResponseEntity<List<Voiture>> getCarNamesForUser(@PathVariable int conductorId) {
        List<Voiture> carNames = new ArrayList<>();

        try {
            Optional<Conductor> conductorOptional = this.conductorRepository.findById(conductorId);
            if (conductorOptional.isPresent()) {
                Conductor conductor =conductorOptional.get();
                List<Voiture> list= this.voitureRepository.findByConductor(conductor);
                carNames.addAll(list);
                return ResponseEntity.ok(carNames);
            } else {
                throw new RuntimeException(ExceptionConductor.ConductorNotFound + conductorId);
            }
        } catch (Exception var6) {
            System.out.println( ExceptionConductor.ErrorFetchCar+ var6.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @DeleteMapping(path = {"deleteVoitureById/{id}"})
    public ResponseEntity<Object> deleteVoiture(@PathVariable Integer id) {
        try {
            Optional<Voiture> optionalVoiture = voitureRepository.findById(id);

            if (optionalVoiture.isPresent()) {
                Voiture voiture = optionalVoiture.get();
                voitureRepository.delete(voiture);
                return ResponseEntity.ok().body("{\"message\": \"Voiture supprimée avec succès\"}");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            System.out.println(  ex.getMessage());
            throw ex;
        }
    }


    @PostMapping({"Reserve"})
    public ResponseEntity<?> Reserve(@RequestBody ReserveDto dto) {
        System.out.println(dto);
        if (dto != null) {
            this.reserveService.ReserveCovoiturage(dto);
            return ResponseEntity.ok("{ \"message\": \"Reserve avec succès\" }");
        } else {
            return null;
        }
    }

    @DeleteMapping(path = {"deleteReserveById/{id}"})
    public ResponseEntity<String> deleteReserve(@PathVariable Integer id) {
        try {
            return this.reserveService.deleteReserver(id);
        } catch (Exception var3) {
            System.out.println(ExceptionConductor.Error_DeleteUser);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping({"getListReserve"})
    public ResponseEntity<List<Reserve>> getListReserve() {
        try {
            List<Reserve> list = this.reserveService.getAllReserve();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error_GetAllVoiture");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping({"getSpecificReserve/{ID}"})
    public ResponseEntity<Reserve> getSpecificReserve(@PathVariable Integer ID) {
        return this.reserveService.getSpecificReserve(ID);
    }


    @PostMapping({"ajouterVoiture"})
    public ResponseEntity<?> ajouterVoiture(@RequestBody VoitureDTO dto) {
        System.out.println(dto);
        if (dto != null) {
            this.voitureService.saveVoiture(dto);
            return ResponseEntity.ok("{ \"message\": \"Ajouter voiture avec succès\" }");
        } else {
            return null;
        }
    }
    @PutMapping("updateVoiture")
    public ResponseEntity<Voiture> updateVoiture(@RequestBody VoitureDTO dto) {
        try {
            Voiture voiture = this.voitureService.updateVoiture(dto);
            return voiture != null ? ResponseEntity.ok(voiture) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception var3) {
            System.out.println(ExceptionConductor.Error_UpdateVoiture+var3.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping(path = {"deletePublicationById/{id}"})
    public ResponseEntity<Object> deletePublicationById(@PathVariable Integer id) {
        try {
            Optional<Publication> publication = this.publicationRepository.findById((long)id);

            if (publication.isPresent()) {

                publicationRepository.delete(publication.get());
                return ResponseEntity.ok().body("{\"message\": \"publication supprimée avec succès\"}");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            System.out.println(  ex.getMessage());
            throw ex;
        }
    }
}
