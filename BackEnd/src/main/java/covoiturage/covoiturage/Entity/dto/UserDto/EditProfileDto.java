package covoiturage.covoiturage.Entity.dto.UserDto;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Data
public class EditProfileDto {
    private int id;
    private String fullname;
    private String email;
    private String password;
    private String tel;
   private String image;
}
