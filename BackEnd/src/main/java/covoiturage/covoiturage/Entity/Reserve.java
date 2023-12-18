package covoiturage.covoiturage.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@DynamicUpdate
@DynamicInsert
@Entity(name = "_reserve")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreserve", unique = true)
            private int idReserve;

    @ManyToOne
    @JoinColumn(name = "id_conductor")
    private Conductor conductor;

    @ManyToOne
    @JoinColumn(name = "id_publication")
    private Publication publication;
}
