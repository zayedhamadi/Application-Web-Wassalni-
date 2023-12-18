package covoiturage.covoiturage.Service;

import covoiturage.covoiturage.Entity.*;
import covoiturage.covoiturage.Entity.Feedback;
import covoiturage.covoiturage.Entity.dto.FeedbackDto;
import covoiturage.covoiturage.Repository.FeddBackRepository;
import covoiturage.covoiturage.Service.Service.FeddBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeddBackServiceImpl  implements FeddBackService {
private final FeddBackRepository feddBackRepository;


    @Override
    public Feedback saveFeedback(FeedbackDto feddBackDto) {

        Feedback f = Feedback.builder()
                .commantaire(feddBackDto.getCommantaire())
                .emailUser(feddBackDto.getEmailUser())
                .build();

return  this.feddBackRepository.save(f);
    }



    @Override
    public ResponseEntity<String> deleteFeedback(Integer id) {
        try {
            Optional<Feedback> feedback = this.feddBackRepository.findById(id);

            if (feedback.isPresent()) {

                this.feddBackRepository.delete(feedback.get());
                return ResponseEntity.ok("Feedback deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            ex.getMessage();
            throw ex;
        }
    }

    @Override
    public List<Feedback> getAllFeedback() {
        try {
            return new ArrayList<>(this.feddBackRepository.findAll());
        } catch (Exception e) {
            System.out.println( e.getMessage());

        }
        return Collections.emptyList();

    }



}
