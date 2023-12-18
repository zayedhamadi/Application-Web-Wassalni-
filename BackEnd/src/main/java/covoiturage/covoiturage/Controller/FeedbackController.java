package covoiturage.covoiturage.Controller;

import covoiturage.covoiturage.Entity.Feedback;
import covoiturage.covoiturage.Entity.Reserve;
import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Entity.dto.FeedbackDto;
import covoiturage.covoiturage.Entity.dto.VoitureDTO;
import covoiturage.covoiturage.Repository.FeddBackRepository;
import covoiturage.covoiturage.Service.ConductorServiceImpl;
import covoiturage.covoiturage.Service.EmailNotificationService;
import covoiturage.covoiturage.Service.FeddBackServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
    @RequiredArgsConstructor
    @RequestMapping({"/api/feedback/"})
    @CrossOrigin(origins = {"http://localhost:4200"})
    public class FeedbackController {
        private final FeddBackServiceImpl feddBackService;
        private final FeddBackRepository feddBackRepository;


    @PostMapping({"ajouterFeddback"})
        public ResponseEntity<?> ajouterFeddback(@RequestBody FeedbackDto dto) {
            System.out.println(dto);
            if (dto != null) {
                this.feddBackService.saveFeedback(dto);
                return ResponseEntity.ok("{ \"message\": \"Ajouter Feedback avec succès\" }");
            } else {
                return null;
            }
        }

    @DeleteMapping(path = {"deleteFeddbackById/{id}"})
    public ResponseEntity<Object> deletefeedback(@PathVariable Integer id) {
        try {
            Optional<Feedback> feedback = this.feddBackRepository.findById(id);

            if (feedback.isPresent()) {
                this.feddBackRepository.delete(feedback.get());
                return ResponseEntity.ok().body("{\"message\": \"feedback supprimée avec succès\"}");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    @GetMapping({"getListFeeddback"})
    public ResponseEntity<List<Feedback>> getListFeedback() {
        try {
            List<Feedback> list = this.feddBackService.getAllFeedback();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error_getListFeedback");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
