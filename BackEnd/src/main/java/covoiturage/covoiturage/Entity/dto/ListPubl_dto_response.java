package covoiturage.covoiturage.Entity.dto;

import covoiturage.covoiturage.Entity.Conductor;
import covoiturage.covoiturage.Entity.Enum.Ville;
import covoiturage.covoiturage.Entity.Voiture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ListPubl_dto_response {


    private int idpub;

    private Date dateCreation;
    private String dateDepart;
    private String description;

    private int nbrePlaceDispo;

    private Ville villeDepart;

    private Ville villeArrive;

    private boolean bagage;

    private boolean climatiser;

    private boolean animal;

    private Double prix;

    private Conductor conductor;

    private Voiture voiture;

}