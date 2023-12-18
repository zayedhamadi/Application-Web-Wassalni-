package covoiturage.covoiturage.Entity.dto;

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
public class VoitureDTO {
    private int idVoiture;
    private String marqueVoiture;
    private String descriptionVoiture;
    private int idConductor;
}