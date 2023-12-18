package covoiturage.covoiturage.Service.Service;

import covoiturage.covoiturage.Entity.dto.AuthDto.SigninDto;
import covoiturage.covoiturage.Entity.dto.AuthDto.SignupDto;
import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.dto.UserDto.EditProfileDto;
import org.springframework.http.ResponseEntity;

public interface ConductorService {
    Conductor signUp(SignupDto signup);

    ResponseEntity<Conductor> SpecificConductor(Integer ID);

    boolean hasCustomerWithEmail(String email);

    Conductor updateConductor(EditProfileDto updatedCustomer);

    ResponseEntity<String> deleteCustomer(Integer id);

    Conductor authenticate(SigninDto signinDto);



}
