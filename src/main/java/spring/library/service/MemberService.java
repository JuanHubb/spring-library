package spring.library.service;

import spring.library.dto.MemberDto;
import spring.library.domain.Member;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import spring.library.repository.MemberRepository;
import spring.library.controller.request.MemberRequest;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto createMember(MemberRequest memberRequest){
//        if (isManager(Member.convertToMember(memberRequest))){
            return MemberDto.convertToMemberDto(memberRepository.save(Member.convertToMember(memberRequest)));
//        }else{
//            System.out.println("접근 권한이 없습니다.");
//            return null;
//        }
    }

    public MemberDto getMemberById(Long id){
        return MemberDto.convertToMemberDto(memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.")));
    }

    public List<MemberDto> getAllMembers(){
        return memberRepository.findAll().stream().map(MemberDto::convertToMemberDto).toList();
    }

    @Transactional
    public MemberDto updateMemberById(Long id, MemberRequest memberRequest){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return MemberDto.convertToMemberDto(member.update(memberRequest));
    }

    public void deleteMemberById(Long id){
//        if (isManager(
//        memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.")))){
            memberRepository.deleteById(id);
//        }else{
//            System.out.println("접근 권한이 없습니다.");
//        }
    }


    public Boolean isManager(Member member){
        return member.getFeature().equals("관리자");
    }
}