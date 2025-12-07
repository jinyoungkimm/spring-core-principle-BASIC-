package hello.core.member;

public interface MemberRepository {

    /** 会員登録
     * @param member　: 会員登録の内容
     */
    void save(Member member);

    /** 会員照会
     * @param id　: 照会対象の会員ID(UNIQUE KEY)
     * @return ：　照会した会員レコード
     */
    Member findById(Long id);
}
