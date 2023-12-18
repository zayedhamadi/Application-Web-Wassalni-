package covoiturage.covoiturage.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import covoiturage.covoiturage.Entity.Enum.Ville;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@DynamicUpdate
@DynamicInsert
@Entity(name = "_publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpub", unique = true)
    private int idpub;

    @NotNull
    private Date dateCreation;

    @NotNull(message = "La date de départ ne peut pas être nulle")
    private String dateDepart;

    @NotBlank(message = "La description ne peut pas être vide")
    private String description;

    @Min(value = 1L, message = "Le nombre de places disponibles doit être d'au moins 1")
    private int nbrePlaceDispo;

//    private String imageVehicule;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "La ville de départ ne peut pas être nulle")
    private Ville villeDepart;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "La ville d'arrivée ne peut pas être nulle")
    private Ville villeArrive;

    private boolean bagage;

    private boolean climatiser;

    private boolean animal;

    @DecimalMin(value = "0.0", message = "Le prix doit être d'au moins 0")
    private Double prix;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idConductor")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Conductor conductor;

    @ManyToOne
    @JoinColumn(name = "id_voiture")
    @JsonBackReference
    private Voiture voiture;

    @OneToMany(mappedBy = "publication")
    private Set<Reserve> reserves = new HashSet<>();


}
