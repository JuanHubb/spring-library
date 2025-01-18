package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import request.MemberRequest;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    public Member update(MemberRequest memberRequest) {
        name = memberRequest.getName();
        idNumber = memberRequest.getIdNumber();
        feature = memberRequest.getFeature();
        email = memberRequest.getEmail();
        phoneNumber = memberRequest.getPhoneNumber();
        return this;
    }

    public static Member convertToMember(MemberRequest memberRequest){
        return Member.builder()
                .name(memberRequest.getName())
                .idNumber(memberRequest.getIdNumber())
                .feature(memberRequest.getFeature())
                .email(memberRequest.getEmail())
                .phoneNumber(memberRequest.getPhoneNumber())
                .build();
    }
}
