package covoiturage.covoiturage.Service;

import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Enum.UserRole;
import covoiturage.covoiturage.Entity.Publication;
import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Entity.dto.AuthDto.SigninDto;
import covoiturage.covoiturage.Entity.dto.AuthDto.SignupDto;
import covoiturage.covoiturage.Exception.ExceptionConductor;
import covoiturage.covoiturage.Repository.ConductorRepository;
import covoiturage.covoiturage.Repository.PublicationRepository;
import covoiturage.covoiturage.Repository.VoitureRepository;
import covoiturage.covoiturage.Service.Service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    private final ConductorRepository conductorRepository;

    @Autowired
    private  final PublicationRepository publicationRepository;

    @Autowired
    private final org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder BCryptPasswordEncoder;

    @Autowired
    private final VoitureRepository voitureRepository;

    @Override
    public boolean hasCustomerWithEmail(String email) {
        Optional<Conductor> optional = Optional.ofNullable(conductorRepository.getByEmail(email));
        return optional.isPresent();
    }

    @Override
    public Conductor signUpAdmin(SignupDto signup) {
        Conductor conductor =
                Conductor.builder()
                .email(signup.getEmail())
                .tel(signup.getTel())
                .fullname(signup.getFullname())
                .password(BCryptPasswordEncoder.encode(signup.getPassword()))
                .userRole(UserRole.ADMIN)
                .build();
        System.out.println("Success step");
        return this.conductorRepository.save(conductor);
    }

    @Override
    public List<Conductor> getAllUser() {
        try {

            List<Conductor> allConductors = this.conductorRepository.findAll();


            return allConductors.stream().filter(conductor -> conductor.getUserRole().equals(UserRole.CONDUCTOR)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(ExceptionConductor.Error_GetAllUser + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public ResponseEntity<String> deleteCustomerByAdmin(Integer id) {
        ResponseEntity<String> response = null;

        try {
            Optional<Conductor> optionalConductor = this.conductorRepository.findById(id);

            if (optionalConductor.isPresent()) {
                Conductor conductor = optionalConductor.get();

                if (conductor.getUserRole() != null && conductor.getUserRole().equals(UserRole.CONDUCTOR)) {
                    this.conductorRepository.delete(conductor);
                    return ResponseEntity.ok().body("{\"message\": \"User supprimée avec succès\"}");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user is not a conductor!");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteVoitureByAdmin(Integer id) {
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
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<Publication> getAllPublication() {
        try {
            return new ArrayList<>(this.publicationRepository.findAll());
        } catch (Exception e) {
            System.out.println( e.getMessage());

        }
        return Collections.emptyList();

    }


    @Override
    public Conductor authenticate(SigninDto signinDto) {
        Conductor conductor = this.conductorRepository.getByEmail(signinDto.getEmail());
//conductor.getPassword().equals()
        try {
            if (conductor != null && BCryptPasswordEncoder.matches(signinDto.getPassword(), conductor.getPassword())) {
                System.out.println(conductor);
                return conductor;
            }
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }

        return null;
    }


}
