package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * testパッケージ以下すべてのテストを実行するとここのテストも走る
 * →　@SpringBootTestは一旦Springサーバーを立ち上げてテストを実行する
 * → 今は最小限のSpringライブラリしかないのでテストの実行時間が短いが本格的にSpring開発が始まると関連ライブラリが増えて時間負荷が増える
 */

@SpringBootTest
class CoreApplicationTests {

	@Test
	void contextLoads() {
	}

}
