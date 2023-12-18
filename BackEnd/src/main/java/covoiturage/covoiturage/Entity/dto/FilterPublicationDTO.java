package covoiturage.covoiturage.Entity.dto;


import covoiturage.covoiturage.Entity.Enum.Ville;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;


@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FilterPublicationDTO {

    private Ville villeDepart;

    private Ville villeArrive;

  //  private String dateDepart;

}
