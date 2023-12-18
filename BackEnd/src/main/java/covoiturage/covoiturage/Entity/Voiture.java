package covoiturage.covoiturage.Entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "_voiture")
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int idVoiture;

    @Column(name = "marqueVoiture", nullable = false)
    private String marqueVoiture;

    @Column(name = "descriptionVoiture", nullable = false)
    private String descriptionVoiture;

    @ManyToOne
    @JoinColumn(name = "idConductor")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Conductor conductor;

    @OneToMany(mappedBy = "voiture", cascade = CascadeType.ALL)
    private Set<Publication> publications = new HashSet<>();
}
