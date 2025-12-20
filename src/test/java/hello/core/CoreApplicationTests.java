package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * testパッケージ以下すべてのテストを実行するとここのテストも走る
 * →　@SpringBootTestは一旦Springサーバーを立ち上げてテストを実行する
 * → 今は最小限のSpringライブラリしかないのでテストの実行時間が短いが本格的にSpring開発が始まると関連ライブラリが増えて時間負荷が増える
 */

/**
 * まずはテストコードすべてが正常動作することを確認
 * →ここでNoUniqueBeanDefinitionエラー発生(MemberMemberRepositoryが二つ登録される)
 * Setting→Build and Test ToolをGradleへ変更すると解決される。
 * Q. 自動スキャンでExcludeFileterオプションを使ってAppConfigクラスはスキャン対象から除外
 * させたのになぜ重複登録されるのか
 *
 * A.@SpringBootTestアノテーションが付くテストコードの実行時にはSpring Bootが作動して
 * Beanが登録される。（そうでないテストコードは純粋なJAVAコードの実行あるいはBootなしで
 * Springフレームワークでの実行だからそういうエラー発生がない）
 * しかし、IntelliJによってビルド、テストコード実行をするように設定したのでSpring Boot
 * の正常動作を妨害していると思われる。
 */

@SpringBootTest
class CoreApplicationTests {

	@Test
	void contextLoads() {
	}

}
