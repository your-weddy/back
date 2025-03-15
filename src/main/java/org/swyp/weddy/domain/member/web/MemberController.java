package org.swyp.weddy.domain.member.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.domain.member.service.MemberService;
import org.swyp.weddy.domain.member.service.dto.MemberEditDto;
import org.swyp.weddy.domain.member.web.request.MemberEditRequest;
import org.swyp.weddy.domain.member.web.response.MemberResponse;

@RestController
@RequestMapping("/member")
public class MemberController implements MemberApiSpec {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateProfileImageUrl(@PathVariable(name = "memberId") Long memberId,
                                                                @RequestBody MemberEditRequest request){
        MemberEditDto memberEditDto = new MemberEditDto(memberId, request.getProfileImageUrl());

        memberService.updateProfileImage(memberEditDto);

        return ResponseEntity.ok().build();
    }


}
