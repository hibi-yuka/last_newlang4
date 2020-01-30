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

	        LexicalAnalyzer lex;//LexicalAnalyzerクラスのlex
	        LexicalUnit		first;//LexicalUnitクラスのfirst
	        Environment		env;//Environmentクラスのenv

	        System.out.println("basic parser");//解析開始

	        String path = "C:\\Users\\c0117312\\Desktop\\text.txt";
			File file = new File(path);
			FileReader fr = null;
			try {
				fr = new FileReader(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			PushbackReader pr = new PushbackReader(fr);
	        lex = new LexicalAnalyzerImpl(pr); //読み込まれた値や文字がlexへ渡されていく,lexにはLexicalType.○○と字句が渡されてる

	        env = new Environment(lex); //読み込んだものを次にenvへ（ここで渡すのは読み込んだものの）

	        first = lex.get();
	        lex.unget(first);

	        if (Program.isFirst(first)) {//<program>というfirst集合に入っているかを確認する

	        	Node handler = Program.getHandler(first, env);
	        	handler.parse();
	        	System.out.println(handler);
	        }
	        else System.out.println("syntax error");//firts集合に該当するものなし＝解析できないのでアウト
	}
}















//call_subに到達してない
//print = 1 は　const print 1,print "hello" などはcall_sub = をungetする
//メモ
//一旦何がどこに入っているのかは考えずに、どの型がどこに渡されているのかを考える。中身は考えない
//分かっていない事
//結局engには何が入っている？
//インスタンスを引数に渡した時に何が渡される？どういう挙動をする？
//目指すべき出力する形
//parseの条件
//vableノード a = b exprに丸投げ、そのexpeが変数保存する為にcontノードに保存