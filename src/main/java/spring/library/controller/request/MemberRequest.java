package spring.library.controller.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;
}
