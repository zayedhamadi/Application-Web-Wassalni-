package covoiturage.covoiturage.Controller;

import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Publication;
import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Entity.dto.AuthDto.SigninDto;
import covoiturage.covoiturage.Entity.dto.AuthDto.SignupDto;
import covoiturage.covoiturage.Entity.dto.AuthResp;
import covoiturage.covoiturage.Exception.ExceptionConductor;
import covoiturage.covoiturage.Repository.PublicationRepository;
import covoiturage.covoiturage.Repository.VoitureRepository;
import covoiturage.covoiturage.Service.AdminServiceImpl;
import covoiturage.covoiturage.Service.VoitureServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/admin/auth/"})
@CrossOrigin(origins = {"http://localhost:4200"})
public class AdminController {

    private final AdminServiceImpl adminService;

    private final VoitureServiceImpl voitureService;

private final PublicationRepository publicationRepository;

    private final VoitureRepository voitureRepository;


     @PostMapping({"signupAdmin"})
    public ResponseEntity<?> createAdmin(@RequestBody SignupDto signUpRequest) {
        if (this.adminService.hasCustomerWithEmail(signUpRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email already exist!Try another one");
        } else {
            Conductor conductor = this.adminService.signUpAdmin(signUpRequest);
            if (conductor == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
            } else {
                System.out.println(conductor);
                return ResponseEntity.status(HttpStatus.CREATED).body(conductor);
            }
        }
    }

    @PostMapping({"signinAdmin"})
    public ResponseEntity<AuthResp> login(@RequestBody SigninDto signinDto) {
        Conductor c = this.adminService.authenticate(signinDto);
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

        @GetMapping("/listConductors")
    public ResponseEntity<?> getAllUsers(){
         try {
             List<Conductor> list= this.adminService.getAllUser();
             if ( list==null){
                 return ResponseEntity.ofNullable(ExceptionConductor.Empty_Table);

             }else return new ResponseEntity<>(list, HttpStatus.OK);

         }catch (Exception e) {System.out.println(ExceptionConductor.Error_GetAllUser);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    }
    @DeleteMapping(path = {"deleteVoitureByAdmin/{id}"})
    public ResponseEntity<Object> deleteVoitureByAdmin(@PathVariable Integer id) {
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
            ex.printStackTrace();
            throw ex;
        }
    }


    @DeleteMapping(path = {"deletePublicationByAdmin/{id}"})
    public ResponseEntity<Object> deletePublicationByAdmin(@PathVariable Integer id) {
        try {
            Optional<Publication> publication = publicationRepository.findById((long)id);

            if (publication.isPresent()) {

                publicationRepository.delete(publication.get());
                return ResponseEntity.ok().body("{\"message\": \"Voiture supprimée avec succès\"}");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    @DeleteMapping(
            path = {"deleteCustomerByAdmin/{ID}"}
    )
    public ResponseEntity<String> deleteUser(@PathVariable Integer ID) {
        try {
           return  this.adminService.deleteCustomerByAdmin(ID);

        } catch (Exception var3) {
            System.out.println(ExceptionConductor.Error_DeleteUser);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @GetMapping(path = "getListVoitureByAdmin")
    public ResponseEntity<List<Voiture>> listVoituresByAdmin() {
        try {
            List<Voiture> userList = voitureService.getAllVoiture();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error_GetAllVoiture");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping(path = "getListPublicationByAdmin")
    public ResponseEntity<List<Publication>> listPublciations() {
        try {
            List<Publication> list = adminService.getAllPublication();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error_GetAllVoiture");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





}
