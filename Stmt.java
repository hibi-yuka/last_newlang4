package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Stmt extends Node {

	Environment env;
	Node handler;

	public Stmt(Environment env) {
		this.env = env;
		type = NodeType.STMT;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.NL
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static Stmt getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new Stmt(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{

		LexicalUnit first = env.getInput().get();//getはLexicalAnalyzerImplの奴
		env.getInput().unget(first);

		if(Subst.isFirst(first)) { //次の判定を開始する
			handler = Subst.getHandler(first ,env);//
			System.out.println(first + " :subst");//出力テスト
			return handler.parse();
		}else if(End.isFirst(first)) {
			System.out.println(first + " : End");
			handler = End.getHandler(first ,env);//
			return handler.parse();
		}
		return true;
	}


	public String toString() {
		return  "stmt" + handler.toString() ;
	}
}
