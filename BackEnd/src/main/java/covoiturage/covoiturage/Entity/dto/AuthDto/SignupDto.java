package covoiturage.covoiturage.Entity.dto.AuthDto;

import covoiturage.covoiturage.Entity.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SignupDto {
    private String email;
    private String tel;
    private String fullname;
    private String password;
    private UserRole userRole;
   // private MultipartFile image;
}
