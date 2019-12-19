package newlang4;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

	        LexicalAnalyzer lex;
	        LexicalUnit		first;
	        Environment		env;

	        System.out.println("basic parser");

	        lex = new LexicalAnalyzerImpl("test.bas");
	        env = new Environment(lex);

	        first = lex.get();//ここでエラー
	        lex.unget(first);//今読んだものが読める

	        if (Program.isFirst(first)) {//<program>というfirst集合に入っているかを確認する
	        	Node handler = Program.getHandler(first, env);//最初の字句と,envを渡す
	        	//envにはLexicalAnalyzerがあるのでそこからgetする。
	        	//先にオブジェクトを作る
	        	handler.parse();//それに向かってparseへ
	        	//作ったオブジェクト利用する
	        	System.out.println(handler); //handler.toStringで出力される

	        }
	        else System.out.println("syntax error");//入ってなかったら文法エラーで
	}

}
