package newlang4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PushbackReader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

	        LexicalAnalyzer lex;
	        LexicalUnit		first;
	        Environment		env;

	        System.out.println("basic parser");//この文字列を持っているの


	        String path = "C:\\Users\\c0117312\\Desktop\\t.txt";
			File file = new File(path);
			FileReader fr = null;
			try {
				fr = new FileReader(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			PushbackReader pr = new PushbackReader(fr);

	        lex = new LexicalAnalyzerImpl(pr);
	        env = new Environment(lex);

	        first = lex.get();//
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
