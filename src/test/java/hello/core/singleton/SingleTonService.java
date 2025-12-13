package hello.core.singleton;

/**
 * 初級開発者が new を使って外部から生成しようとするとコンパイルエラーになる
 * → 最も望ましいエラーはコンパイルエラー
 * （エラーの大半をコンパイルエラーにするためには、良い設計が前提となる）
 */
class SingleTonService {
    private static final SingleTonService instance = new SingleTonService();

    public static SingleTonService getInstance() {
        return instance;
    }

    // 外部でSingleTonServiceオブジェクト生成は不可
    private SingleTonService() {}

    public void logic() {
        System.out.println("SingleTonService　：　Logic実行");
    }
}

/**
 * 【シングルトンパターンの様々な問題点】
 * ・シングルトンパターンを実装するためのコード量が多くなる。
 * → 本当に必要な機能は【logicメソッド】だけ！
 *
 * ・依存関係の観点で、クライアントが実装クラスに依存してしまい、DIP に違反する。
 * → private static final SingleTonService instance = new SingleTonService()で実装クラスへ依存している
 *
 * ・クライアントが実装クラスに依存するため、OCP 原則に違反する可能性が高い(memo参照)
 * → private static final SingleTonService instance = new SingleTonService()で実装クラスへ依存している
 *
 * ・テストがしにくい。
 * → finalによりクラスローディング時点で初期化が一度しか起きないので、多様なテストケースを作ることが出来ない
 *
 * ・内部状態の変更や初期化が困難である。
 * → finalによりクラスローディング時点で初期化が一度しか起きない
 *
 * ・private コンストラクタのため、サブクラスを作成しにくい。
 * →　サブクラスでコンストラクタ呼び出しが出来ない。
 *
 * ・結果として柔軟性が低い。
 * →　DIP に違反しているため。
 *
 * ・アンチパターンと呼ばれることもある。
 *
 * ※ Spring Containerは上記の問題点をすべて解決した
 */
