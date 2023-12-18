package covoiturage.covoiturage.Service.Service;


import covoiturage.covoiturage.Entity.Feedback;
import covoiturage.covoiturage.Entity.dto.FeedbackDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeddBackService {

    Feedback saveFeedback(FeedbackDto feddBackDto);
    ResponseEntity<String> deleteFeedback(Integer id);


    List<Feedback> getAllFeedback();
}
