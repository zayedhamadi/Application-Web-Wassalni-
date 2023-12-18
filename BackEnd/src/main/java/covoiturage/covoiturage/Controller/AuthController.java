package covoiturage.covoiturage.Controller;


import covoiturage.covoiturage.Entity.dto.AuthDto.SigninDto;
import covoiturage.covoiturage.Entity.dto.AuthDto.SignupDto;
import covoiturage.covoiturage.Entity.dto.AuthResp;
import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Exception.ExceptionConductor;
import covoiturage.covoiturage.Repository.ConductorRepository;
import covoiturage.covoiturage.Service.ConductorServiceImpl;
import covoiturage.covoiturage.Service.EmailNotificationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/auth/"})
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {
    private final ConductorServiceImpl conductorService;
    private final EmailNotificationService emailNotificationService;



    @PostMapping("signupConductor")
    public ResponseEntity<?> createCustomer(@RequestBody SignupDto signUpRequest) {
        if (conductorService.hasCustomerWithEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ExceptionConductor.emailduplicated);
        } else {
            Conductor conductor = conductorService.signUp(signUpRequest);
            if (conductor == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionConductor.Bad_Request);
            } else {
                emailNotificationService.sendNotificationEmail(
                        conductor.getEmail(),
                        "Welcome to Wassalni!",
                        "Dear " + conductor.getFullname() + ",\n\n" +
                                "Thank you for signing up with Wassalni! We're thrilled to have you join our community.\n\n" +
                                "Your account has been successfully created, and you're now part of Wassalni's platform. " +
                                "You can access your login page by clicking the link below:\n\n" +
                                "Login Page: http://localhost:4200/login\n\n" +
                                "If you have any questions, concerns, or need assistance, please don't hesitate to contact us. " +
                                "We're here to help.\n\n" +
                                "Once again, welcome aboard!\n\n" +
                                "Best regards,\n" +
                                "Wassalni Team"
                );

                return ResponseEntity.status(HttpStatus.CREATED).body(conductor);
            }
        }
    }

    @PostMapping({"signinConductor"})

    public ResponseEntity<AuthResp> login(@RequestBody SigninDto signinDto) {
        Conductor c = this.conductorService.authenticate(signinDto);
        AuthResp resp;
        if (c != null) {
            resp = AuthResp.builder()
                    .idConductor(c.getIdConductor())
                    .email(c.getEmail())
                    .message("Success")
                    .build();
        } else {
            System.out.println("erreur");
            resp = AuthResp.builder().message(ExceptionConductor.AccessDenied).build();
        }

        return ResponseEntity.ok(resp);
}
}

   /* @PostMapping({"signup"})
    public ResponseEntity<?> createCustomer(@RequestBody SignupDto signUpRequest) {
        if (this.conductorService.hasCustomerWithEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email already exist!Try another one");
        } else {
            Conductor conductor = this.conductorService.signUp(signUpRequest);
            if (conductor == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
            } else {
                System.out.println(conductor);
                return ResponseEntity.status(HttpStatus.CREATED).body(conductor);
            }
        }
    }*/