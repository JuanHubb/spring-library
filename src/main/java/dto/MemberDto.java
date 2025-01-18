package dto;

import domain.Member;
import lombok.*;
import request.MemberRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    public static MemberDto convertToMemberDto(Member member){
        return MemberDto.builder()
                .name(member.getName())
                .idNumber(member.getIdNumber())
                .feature(member.getFeature())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
