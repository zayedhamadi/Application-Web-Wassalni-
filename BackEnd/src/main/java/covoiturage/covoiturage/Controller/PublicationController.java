package covoiturage.covoiturage.Controller;


import covoiturage.covoiturage.Entity.Publication;
import covoiturage.covoiturage.Entity.dto.FilterPublicationDTO;
import covoiturage.covoiturage.Entity.dto.ListPubl_dto_response;
import covoiturage.covoiturage.Entity.dto.PublicationDto;
import covoiturage.covoiturage.Service.PublicationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/pub/"})
@CrossOrigin(
        origins = {"http://localhost:4200"}
)
public class PublicationController {

    private final  PublicationServiceImpl publicationService;


    @GetMapping( "Listpub")
    public ResponseEntity<List<ListPubl_dto_response>> listPub( ){
        System.out.println("start");
     List<ListPubl_dto_response>  a=this.publicationService.getListPub();

    return ResponseEntity.ok(a) ;

    }



    @PostMapping("filterpub")
    public List<ListPubl_dto_response> filterPublications(@RequestBody FilterPublicationDTO FilterPublicationDTO) {
        return this.publicationService.FiltrerAndGetListPub(FilterPublicationDTO);
    }




}



