package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Expr_List extends Node{

	Environment env;
	Node handler;

	public Expr_List(Environment env) {
		this.env = env;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
		LexicalType.NAME
		);

		public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

		public static Call_sub getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new Call_sub(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{ //三つ目のメソッド

		LexicalUnit first = env.getInput().get();//getはLexicalAnalyzerImplの奴
		env.getInput().unget(first);

		if(first.getType() == LexicalType.COMMA) {//EQはここで処理をしてしまうので、ungetして次のNodeに渡す必要なし
			System.out.println(first + " :COMMA");
		}

		first = env.getInput().get();//getはLexicalAnalyzerImplの奴
		env.getInput().unget(first);

		if(Expr.isFirst(first)) { //エクペア―
			handler = Expr.getHandler(first ,env);//
			System.out.println(first + " :expr.Expr");//出力テスト
			handler.parse();
		}
		return true;
		}
}


