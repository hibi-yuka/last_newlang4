package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class VarList extends Node {

	Environment env;

	public VarList(Environment env) {
		this.env = env;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}


	public static VarList getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new VarList(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{

		LexicalUnit first = env.getInput().get();
		//ここでツリーを作る

			return false;
	}
}