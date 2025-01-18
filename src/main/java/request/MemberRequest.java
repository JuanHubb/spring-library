package request;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
public class MemberRequest {
    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;
}
