package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Call_fun extends Node{



	static final Set<LexicalType> fristSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
			LexicalType.NAME
			);

	public Call_fun(){

	}

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}


	public static Stmt getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new Stmt(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{

		return false;
	}
}


