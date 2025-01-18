package service;

import dto.MemberDto;
import domain.Member;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import repository.MemberRepository;
import request.MemberRequest;

import java.util.List;

@Service
@Getter
@Setter
public class MemberService {
    private MemberRepository memberRepository;

    public MemberDto createMember(MemberRequest memberRequest){
        return MemberDto.convertToMemberDto(memberRepository.save(Member.convertToMember(memberRequest)));

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
        memberRepository.deleteById(id);
    }
}