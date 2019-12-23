package newlang4;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class Expr_List extends Node{

	Environment env;
	Node handler;
	public List<Node> handlerlist = new ArrayList<Node>();

	public Expr_List(Environment env) {
		this.env = env;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.SUB,
			LexicalType.LP,
			LexicalType.NAME
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static Expr_List getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new Expr_List(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{ //三つ目のメソッド

		LexicalUnit first = env.getInput().get();//getはLexicalAnalyzerImplの奴
		env.getInput().unget(first);

		if(Expr.isFirst(first)) {
			handler = Const.getHandler(first ,env);//
			System.out.println(first + " : Expr");//出力テスト
			handler.parse();
			handlerlist.add(handler);//
		}else  if(first.getType() == LexicalType.COMMA) {//EQはここで処理をしてしまうので、ungetして次のNodeに渡す必要なし
			System.out.println(first + " :COMMA");
		}

		first = env.getInput().get();//getはLexicalAnalyzerImplの奴
		env.getInput().unget(first);

		if(Expr.isFirst(first)) { //ィ―エックスぴーあーる―
			handler = Expr.getHandler(first ,env);//
			System.out.println(first + " :expr.Expr");//出力テスト
			handler.parse();
		}
		return true;
	}
}

//
