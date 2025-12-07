package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    /**
     *  @BeforeEach : 各テストの実行の前に実行
     *  → 複数のテストを実行するため、MemberServiceでデータの不一致が発生する恐れが存在
     *  → 各テストごとにMemberServiceオブジェクトを新しく生成するのが望ましい
     */
    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //givien
        Member member = new Member(1L, "jyk", Grade.BASIC);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
