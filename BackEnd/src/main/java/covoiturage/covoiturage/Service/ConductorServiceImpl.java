package covoiturage.covoiturage.Service;

import covoiturage.covoiturage.Entity.Enum.UserRole;
import covoiturage.covoiturage.Entity.dto.AuthDto.SigninDto;
import covoiturage.covoiturage.Entity.dto.AuthDto.SignupDto;
import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.dto.UserDto.EditProfileDto;
import covoiturage.covoiturage.Repository.ConductorRepository;
import covoiturage.covoiturage.Service.Service.ConductorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class ConductorServiceImpl implements ConductorService {
    @Autowired
    private final ConductorRepository conductorRepository;

    @Autowired
    private final org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder BCryptPasswordEncoder;

    public Conductor signUp(SignupDto signup) {

        Conductor conductor = Conductor.builder()
                .email(signup.getEmail())
                .tel(signup.getTel())
                .fullname(signup.getFullname())
                .password(BCryptPasswordEncoder.encode(signup.getPassword()))
                .userRole(UserRole.CONDUCTOR)
                .build();
        System.out.println("Success step");
        return this.conductorRepository.save(conductor);
    }


    private void updateFieldIfValid(Conductor existingUser, String updatedValue, Consumer<String> fieldSetter) {
        if (updatedValue != null && !updatedValue.isEmpty()) {
            fieldSetter.accept(updatedValue);
        }

    }

    public ResponseEntity<Conductor> SpecificConductor(Integer ID) {
        Optional<Conductor> optionalEmployee = this.conductorRepository.findById(ID);
        return optionalEmployee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        Optional<Conductor> optional = Optional.ofNullable(conductorRepository.getByEmail(email));
        return optional.isPresent();
    }



    @Override
    public Conductor updateConductor(EditProfileDto updatedCustomer) {
        Integer userId = updatedCustomer.getId();

        Optional<Conductor> optionalConductor = conductorRepository.findById(userId);

        if (optionalConductor.isPresent()) {
            Conductor existingUser = optionalConductor.get();
            updateFieldIfValid(existingUser, updatedCustomer.getTel(), existingUser::setTel);
            updateFieldIfValid(existingUser, updatedCustomer.getFullname(), existingUser::setFullname);
            updateFieldIfValid(existingUser, updatedCustomer.getImage(), existingUser::setImage);
            updateFieldIfValid(existingUser, updatedCustomer.getEmail(), existingUser::setEmail);
            String updatedPassword = updatedCustomer.getPassword();
            if (updatedPassword != null && !updatedPassword.isEmpty()) {
                String encodedPassword = BCryptPasswordEncoder.encode(updatedPassword);
                existingUser.setPassword(encodedPassword);
            }
            return conductorRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteCustomer(Integer id) {
        ResponseEntity<String> response = null;
        try {
            Optional<Conductor> optionalConductor = this.conductorRepository.findById(id);
            if (optionalConductor.isPresent()) {
                this.conductorRepository.delete(optionalConductor.get());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully!");

            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user: " + e.getMessage());
        }
        return response;
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