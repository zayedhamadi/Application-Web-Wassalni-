package covoiturage.covoiturage.Entity.dto.UserDto;

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
public class ConductorDto {
    private int id;
    private String fullname;
    private String email;
    private String password;
    private String tel;

//    private String image;

}
