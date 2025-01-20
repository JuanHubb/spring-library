package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.dto.MemberDto;

@Getter
@Setter
@Builder
public class MemberResponse {
    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    public static MemberResponse convertToMemberResponse(MemberDto memberDto){
        return MemberResponse.builder()
                .name(memberDto.getName())
                .idNumber(memberDto.getIdNumber())
                .feature(memberDto.getFeature())
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }
}
