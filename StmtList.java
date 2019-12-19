package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class StmtList extends Node {

	Environment env;

	public StmtList(Environment env) {
		this.env = env;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}


	public static StmtList getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ

		return new StmtList(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{

		LexicalUnit first = env.getInput().get();
		//ここでツリーを作る
		while(true) {
		if(Stmt.isFirst(first)) {
			Node handler = Stmt.getHandler(first , env);
			return handler.parse();
		}else {
			break;//
		}
	}
	return false;
	}
}


