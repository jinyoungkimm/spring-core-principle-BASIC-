package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    /**
     *  DIP遵守 : インターフェースだけに依存
     *  → fianlでもコンストラクタにより初期化すれば、コンパイラーエラーは発生しない
     */
    private final MemberRepository memberRepository;

    /**
     * コンストラクタインジェクション
     */
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //　シングルトンのテスト用
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}
