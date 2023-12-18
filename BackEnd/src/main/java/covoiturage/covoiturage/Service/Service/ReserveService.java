package covoiturage.covoiturage.Service.Service;

import covoiturage.covoiturage.Entity.Reserve;
import covoiturage.covoiturage.Entity.dto.ReserverDto.ReserveDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReserveService {


    Reserve ReserveCovoiturage(ReserveDto v);

    ResponseEntity<String> deleteReserver(Integer id);

    List<Reserve> getAllReserve();

    ResponseEntity<Reserve> getSpecificReserve(Integer id);
}
