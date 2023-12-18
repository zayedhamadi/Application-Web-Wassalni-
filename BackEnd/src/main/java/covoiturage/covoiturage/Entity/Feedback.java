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
@Entity(name = "_Feedback")
public class  Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFeedback", unique = true)
    private int idFeedback;

    @Column(name = "commantaire",nullable = false)
    private String commantaire ;


    @Column(name = "emailUser",nullable = false,unique = true)

    private String emailUser;
}
