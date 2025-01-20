package spring.library.controller;

import spring.library.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.MemberRequest;
import spring.library.controller.response.MemberResponse;
import spring.library.service.MemberService;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberResponse> addMember(@RequestBody MemberRequest memberRequest){
        MemberDto memberDto = memberService.createMember(memberRequest);
        return ResponseEntity.ok().body(MemberResponse.convertToMemberResponse(memberDto));
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

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<MemberResponse> deleteMember(@PathVariable Long memberId){
        memberService.deleteMemberById(memberId);
        return ResponseEntity.ok().build();
    }
}


