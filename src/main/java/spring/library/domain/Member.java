package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.MemberRequest;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    private String name;
    private Long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    @OneToMany(
            mappedBy = "member",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<BookLoan> bookLoans =  new ArrayList<>();

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
