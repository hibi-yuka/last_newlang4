package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Subst extends Node{


	LexicalUnit first;
	Environment env;
	Node handler ;

	public Subst (LexicalUnit first,Environment env) {//コンストラクタ
		this.first = first;
		this.env = env;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static Node getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new Subst(first,env);//StmtListクラスをインスタンス化する
	}


	public boolean parse() throws Exception{ //三つ目のメソッド

		if(Variable.isFirst(first)) { //変数名を見る
			handler = Variable.getHandler(first ,env);//
			handler.parse();
		}

		first = env.getInput().get();//getはLexicalAnalyzerImplの奴

		if(first.getType() != LexicalType.EQ) {//=でないならexitで終了
			System.out.println("EQではありません");//
			System.exit(1);
		}

		first = env.getInput().get(); //  前のが=だった時、次の文字をgetして読み込む

		if(Expr.isFirst(first)) { //式かを判断する
			handler = Expr.getHandler(first ,env);//
			System.out.println(first + " :Subst.expr");//出力テスト
			handler.parse();
			return true;

		}
		System.out.println("解析エラー");
		System.exit(1);
		return false;//エラー発生時にexitで抜けるが、falseがないとコンパイルで引っかかる
	}

	public String toString() {
		return handler.toString();

	}
}
