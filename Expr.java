package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Expr extends Node{

	Environment env;
	Node handler;

	public Expr(Environment env) {
		this.env = env;
		type = NodeType.EXPR;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.RP,
			LexicalType.NAME,
			LexicalType.INTVAL,
			LexicalType.LITERAL,
			LexicalType.DOUBLEVAL
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static Expr getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new Expr(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{ //三つ目のメソッド

		LexicalUnit first = env.getInput().get();
		env.getInput().unget(first);

		handler = Const.getHandler(first ,env);//
		System.out.println(first + " :Expr.Const");//出力テスト
		return handler.parse();

	}

	public String toString() {

		return "ex" + handler.toString();
	}
}
