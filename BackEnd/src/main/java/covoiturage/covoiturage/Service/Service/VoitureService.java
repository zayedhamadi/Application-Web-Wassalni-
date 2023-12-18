package covoiturage.covoiturage.Service.Service;

import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Entity.dto.UserDto.EditProfileDto;
import covoiturage.covoiturage.Entity.dto.VoitureDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VoitureService {
    Voiture saveVoiture(VoitureDTO v);

    List<Voiture> getAllVoitureOfUser();

    Voiture updateVoiture(VoitureDTO dto);

    ResponseEntity<String> deleteVoiture(Integer id);


    List<Voiture> getAllVoiture();


}
