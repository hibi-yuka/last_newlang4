package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Subst extends Node{

	Environment env;

	public Subst(Environment env) {
		this.env = env;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static Subst getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new Subst(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{ //三つ目のメソッド


		return false;
	}
}
