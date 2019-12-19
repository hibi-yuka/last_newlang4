package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Expr extends Node{

	Environment env;

	public Expr(Environment env){
		this.env = env;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.SUB,
			LexicalType.RP,
			LexicalType.NAME,
			LexicalType.LITERAL
			);

	public static boolean isFirst(LexicalUnit lu) { //１つ目のメソッド
		return fristSet.contains(lu.getType()); //Setが指定された要素を保持している場合にtrue
	}

	public static Expr getHandler(LexicalUnit first, Environment env) { //二つ目のメソッドここでは引数が二つ渡されている。最初に読み込んだ
		return new Expr(env);
	}

	public boolean parse() throws Exception{

		return false;
	}
}
