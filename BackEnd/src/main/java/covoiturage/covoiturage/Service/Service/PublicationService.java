package covoiturage.covoiturage.Service.Service;

import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Entity.dto.ListPubl_dto_response;
import covoiturage.covoiturage.Entity.dto.PublicationDto;
import covoiturage.covoiturage.Entity.Publication;
import covoiturage.covoiturage.Entity.dto.UserDto.EditProfileDto;
import covoiturage.covoiturage.Entity.dto.VoitureDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PublicationService {
    Publication savePublication(PublicationDto p);

    List<ListPubl_dto_response> getListPub();

    Publication updatePublication(PublicationDto dto);

    ResponseEntity<String> deletePublication(Integer id);




}

