package covoiturage.covoiturage.Service;


import covoiturage.covoiturage.Entity.Voiture;
import covoiturage.covoiturage.Entity.dto.FilterPublicationDTO;
import covoiturage.covoiturage.Entity.dto.ListPubl_dto_response;
import covoiturage.covoiturage.Entity.dto.PublicationDto;
import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Publication;
import covoiturage.covoiturage.Repository.ConductorRepository;
import covoiturage.covoiturage.Repository.PublicationRepository;
import covoiturage.covoiturage.Repository.VoitureRepository;
import covoiturage.covoiturage.Service.Service.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    ConductorRepository conductorRepository;
    @Autowired
    VoitureRepository voitureRepository;

    public Publication savePublication(PublicationDto p) {
        Conductor conductor = this.conductorRepository.findById(p.getIdConductor()).orElseThrow(() -> {
            return new RuntimeException("Conductor not found");
        });
        Voiture voiture = this.voitureRepository.findById(p.getIdVoiture()).orElseThrow(() -> {
            return new RuntimeException("Voiture not found");
        });
        Publication publication = Publication.builder()
                .conductor(conductor)
                .dateCreation(new Date(System.currentTimeMillis()))
                .dateDepart(p.getDateDepart())
                .description(p.getDescription())
                .nbrePlaceDispo(p.getNbrePlaceDispo())
                .villeArrive(p.getVilleArrive())
                .villeDepart(p.getVilleDepart())
                .animal(p.isAnimal())
                .bagage(p.isBagage())
                .climatiser(p.isClimatiser())
                .prix(p.getPrix())
                .voiture(voiture)
                .build();
        System.out.println(conductor + "  est cree  :" + publication);
        return this.publicationRepository.save(publication);
    }
    @Override
    public List<ListPubl_dto_response> getListPub() {

        List<Publication> listpub = this.publicationRepository.findAll();
        List<ListPubl_dto_response> responseList = new ArrayList<>();

        for (Publication i : listpub) {
            Conductor c = i.getConductor();
            Voiture v = i.getVoiture();
            ListPubl_dto_response res = ListPubl_dto_response.builder()
                    .idpub(i.getIdpub())
                    .prix(i.getPrix())
                    .voiture(v)
                    .conductor(c)
                    .dateCreation(i.getDateCreation())
                    .dateDepart(i.getDateDepart())
                    .description(i.getDescription())
                    .nbrePlaceDispo(i.getNbrePlaceDispo())
                    .villeArrive(i.getVilleArrive())
                    .villeDepart(i.getVilleDepart())
                    .animal(i.isAnimal())
                    .bagage(i.isBagage())
                    .climatiser(i.isClimatiser())
                    .build();
            responseList.add(res);

        }
        System.out.println(responseList);
        return responseList;
    }



    public List<ListPubl_dto_response> FiltrerAndGetListPub(FilterPublicationDTO filterPublicationDTO) {
        List<Publication> listpub = this.publicationRepository.findAll();

        List<ListPubl_dto_response> responseList = listpub.stream()
                .filter(publication -> {
                    boolean isVilleDepartMatch = filterPublicationDTO.getVilleDepart() == null ||
                            filterPublicationDTO.getVilleDepart().equals(publication.getVilleDepart());

                    boolean isVilleArriveMatch = filterPublicationDTO.getVilleArrive() == null ||
                            filterPublicationDTO.getVilleArrive().equals(publication.getVilleArrive());



                    return isVilleDepartMatch && isVilleArriveMatch;
                })
                .map(publication -> {
                    Conductor c = publication.getConductor();
                    Voiture v = publication.getVoiture();
                    return ListPubl_dto_response.builder()
                            .idpub(publication.getIdpub())
                            .prix(publication.getPrix())
                            .voiture(v)
                            .conductor(c)
                            .dateCreation(publication.getDateCreation())
                            .dateDepart(publication.getDateDepart())
                            .description(publication.getDescription())
                            .nbrePlaceDispo(publication.getNbrePlaceDispo())
                            .villeArrive(publication.getVilleArrive())
                            .villeDepart(publication.getVilleDepart())
                            .animal(publication.isAnimal())
                            .bagage(publication.isBagage())
                            .climatiser(publication.isClimatiser())
                            .build();
                })
                .collect(Collectors.toList());

        System.out.println(responseList);
        return responseList;
    }
    @Override
    public ResponseEntity<String> deletePublication(Integer id) {
        try {
            Optional<Publication> publication = publicationRepository.findById((long)id);

            if (publication.isPresent()) {

                publicationRepository.delete(publication.get());
                return ResponseEntity.ok("Publication deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }


    @Override
    public Publication updatePublication(PublicationDto dto) {
        return null;
    }


}
