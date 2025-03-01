package org.swyp.weddy.domain.member.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swyp.weddy.domain.member.service.MemberService;
import org.swyp.weddy.domain.member.web.response.MemberResponse;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PatchMapping
    public ResponseEntity<MemberResponse> updateProfileImageUrl(@PathVariable Long memberId,
                                                                @RequestParam("profileImgUrl") String profileImgUrl){
        return null;
    }


}
