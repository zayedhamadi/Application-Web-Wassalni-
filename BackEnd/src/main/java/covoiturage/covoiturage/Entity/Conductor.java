package covoiturage.covoiturage.Entity;

import com.fasterxml.jackson.annotation.*;
import covoiturage.covoiturage.Entity.Enum.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


@Builder
@Data
@Entity(name = "_conductor")
@NoArgsConstructor
@AllArgsConstructor
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconductor", unique = true)
    private int idConductor;

    private String fullname;

    @NotBlank(message = "Le champ mail ne peut pas être vide")
    @Email(message = "Veuillez entrer une adresse email valide")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Veuillez entrer une adresse email valide")
    private String email;

    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide")
    @Pattern(regexp = "\\d{8}", message = "Le numéro de téléphone doit être composé de 8 chiffres")
    private String tel;

    private String password;


   private String image;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Publication> publications = new HashSet<>();

    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Voiture> voitures = new HashSet<>();

    @OneToMany(mappedBy = "conductor")
    private Set<Reserve> reserves = new HashSet<>();


}
