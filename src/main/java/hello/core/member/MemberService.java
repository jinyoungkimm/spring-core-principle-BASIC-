package hello.core.member;

public interface MemberService {
    /** 新規会員登録
     * @param member　: 会員登録の内容
     */
    void join(Member member);

    /** 会員照会
     * @param memberId　: 照会対象の会員ID
     * @return ：　照会対象の会員情報
     */
    Member findMember(Long memberId);
}
