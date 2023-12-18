package covoiturage.covoiturage;

import covoiturage.covoiturage.Controller.AdminController;
import covoiturage.covoiturage.Controller.AuthController;
import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Publication;
import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Repository.ConductorRepository;
import covoiturage.covoiturage.Service.AdminServiceImpl;
import covoiturage.covoiturage.Service.ConductorServiceImpl;
import covoiturage.covoiturage.Service.VoitureServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class CovoiturageApplicationTests {
    @Mock
    private ConductorServiceImpl conductorService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private ConductorRepository conductorRepository;

    @Mock
    private VoitureServiceImpl voitureService;
    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminServiceImpl adminService;

    @InjectMocks
    private AuthController authController;

    public CovoiturageApplicationTests() {
    }


    @Test
    void contextLoads() {
    }


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPublications() {
        List<Publication> list = new ArrayList<>();
        lenient().when(adminService.getAllPublication()).thenReturn(list);

        ResponseEntity<?> responseEntity = adminController.listPublciations();

        // Validate the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(list, responseEntity.getBody());
    }





    @Test
    void getAllConductors() {

        List<Conductor> mockUtilisateurs = new ArrayList<>();
        lenient().when(adminService.getAllUser()).thenReturn(mockUtilisateurs);

        ResponseEntity<?> responseEntity = adminController.getAllUsers();


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUtilisateurs, responseEntity.getBody());
    }


    @Test
    void getAllVoitures() {
        List<Voiture> voitures = new ArrayList<>(); // Create mock data
        lenient().when(voitureService.getAllVoiture()).thenReturn(voitures);

        ResponseEntity<?> responseEntity = adminController.listVoituresByAdmin();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(voitures, responseEntity.getBody());
    }




}



