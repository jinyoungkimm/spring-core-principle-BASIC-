package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

public class MemberApp {


    public static void main(String[] args) {

        MemberServiceImpl memberService = new MemberServiceImpl();
        Member member = new Member(1L, "jyk", Grade.BASIC);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member);
        System.out.println("findMember = " + findMember);


    }


}
