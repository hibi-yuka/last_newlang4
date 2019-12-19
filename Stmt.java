package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Stmt extends Node {

	Environment env;

	public Stmt(Environment env) {
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


	public static Stmt getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new Stmt(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{

		LexicalUnit first = env.getInput().get();
		//ここでツリーを作る
		while(true) {
		if(If_prefix.isFirst(first)) {
			Node handler = If_prefix.getHandler(first , env);
			handler.parse();
		}else if(Subst.isFirst(first)) {
			Node handler = Subst.getHandler(first , env);
			return handler.parse();
		}else if(Call_sub.isFirst(first)) {
			Node handler = Call_sub.getHandler(first , env);
			return handler.parse();
		}
			break;
		}
			return false;
	}
}
