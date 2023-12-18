package covoiturage.covoiturage.Entity.dto;

import covoiturage.covoiturage.Entity.Enum.Ville;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PublicationDto {
    private int idpub;

    private int idConductor;
    private String dateDepart;
    private String description;
    private int nbrePlaceDispo;
    private Ville villeDepart;
    private Ville villeArrive;
    private boolean bagage;
    private boolean climatiser;
    private boolean animal;
    private Double prix;
    private int idVoiture;
}