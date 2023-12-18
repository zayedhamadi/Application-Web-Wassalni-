package covoiturage.covoiturage.Service.Service;

import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Publication;
import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Entity.dto.AuthDto.SigninDto;
import covoiturage.covoiturage.Entity.dto.AuthDto.SignupDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface AdminService {

    Conductor signUpAdmin(SignupDto signup);

    List<Conductor> getAllUser();

    boolean hasCustomerWithEmail(String email);

    Conductor authenticate(SigninDto signinDto);

    ResponseEntity<String> deleteCustomerByAdmin(Integer id);

    ResponseEntity<String> deleteVoitureByAdmin(Integer id);

    List<Publication> getAllPublication();






}