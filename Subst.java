package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Subst extends Node{

	Environment env;
	Node handler;

	public Subst(Environment env) {
		this.env = env;
		type = NodeType.ASSIGN_STMT;
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

		LexicalUnit first = env.getInput().get();//getはLexicalAnalyzerImplの奴
		env.getInput().unget(first);

		if(Const.isFirst(first)) { //変数名を見る
			handler = Const.getHandler(first ,env);//
			System.out.println(first + " :Subst.const");//出力テスト
			handler.parse();
		}

		first = env.getInput().get();//getはLexicalAnalyzerImplの奴

		if(first.getType() == LexicalType.EQ) {//EQはここで処理をしてしまうので、ungetして次のNodeに渡す必要なし
			System.out.println(first + " :EQ");// = であるか見る
		}

		first = env.getInput().get();
		env.getInput().unget(first);

		if(Expr.isFirst(first)) { //式かを判断する
			handler = Expr.getHandler(first ,env);//
			System.out.println(first + " :Subst.expr");//出力テスト
			handler.parse();
		}
		return true;
	}

	public String toString() {
		return "sub " + handler.toString();

	}
}

//public value getValue() throew Exception{
//vakue va1 = handler.getValue();
// handler.setValue(va1);
// return val;
//subst = 変数　= expr で構成されている、変数名は保存する必要がある。
//自分自身が const
// const をNode型で取っておくだけ

//subst　
// = は　EQ なんで　読んで

//次は

// pase(){
//  へんすうめい
// get const.isFirst
//
//  イコール　
// get = かどうか確認
//  //expr
//この処理であるのは直ぐ終わる
