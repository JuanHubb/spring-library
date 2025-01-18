package controller;

import dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.MemberRequest;
import response.MemberResponse;
import service.MemberService;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberResponse> addMember(@RequestBody MemberRequest memberRequest){
        MemberDto memberDto = memberService.createMember(memberRequest);
        return ResponseEntity.ok(MemberResponse.convertToMemberResponse(memberDto));
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long memberId){
        MemberDto memberDto = memberService.getMemberById(memberId);
        return ResponseEntity.ok(MemberResponse.convertToMemberResponse(memberDto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> getAllMembers(){
        List<MemberResponse> members = memberService.getAllMembers().stream().map(MemberResponse::convertToMemberResponse).toList();
        return ResponseEntity.ok(members);
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberRequest memberRequest){
        MemberResponse memberResponse = MemberResponse.convertToMemberResponse(memberService.updateMemberById(memberId, memberRequest));
        return ResponseEntity.ok(memberResponse);
    }

    @DeleteMapping
    public ResponseEntity<MemberResponse> deleteMember(@PathVariable Long memberId){
        memberService.deleteMemberById(memberId);
        return ResponseEntity.noContent().build();
    }
}


