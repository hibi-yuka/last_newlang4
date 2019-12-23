package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Call_sub extends Node{

	Environment env;
	Node handler;

	public Call_sub(Environment env) {
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
	public boolean parse() throws Exception{

	LexicalUnit first = env.getInput().get();//env内のLexicalAnalyzerImpl.get()を呼び出している。
	env.getInput().unget(first);

	if(Const.isFirst(first)) { //次の判定を開始する
		handler = Const.getHandler(first ,env);//
		System.out.println(first + " :const");//出力テスト
		handler.parse();
	}

	first = env.getInput().get();//getはLexicalAnalyzerImplの奴
	env.getInput().unget(first);

	if(Expr_List.isFirst(first)) { //イーエックスピーアール―
		handler = Expr_List.getHandler(first ,env);//
		System.out.println(first + " :expr_list");//出力テスト
		handler.parse();
	}

	return true;
	}

}


