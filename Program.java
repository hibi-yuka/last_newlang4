package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Program extends Node{ //ここに3つのメソッドを書き加えればいい。Nodeは全てのクラスに必要になる

	LexicalUnit first;
	Environment env;
	Node handler ;

	public Program(LexicalUnit first,Environment env) {//コンストラクタ
		this.first = first;
		this.env = env;
		type = NodeType.PROGRAM;
	}

	//Set<E> E - このセットで保持される要素の型
	static final Set<LexicalType> firstSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO
			);

	public static boolean isFirst(LexicalUnit lu) { //１つ目のメソッド
		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception { //二つ目のメソッド

		if(Program.isFirst(first)){ //まずfirst集合を比べて、大丈夫ならPrgramインスタンスが生成される
			return new Program(first,env);
		}
		throw new Exception("StmtListにないfirst集合です");//first集合でない時
	}

	public boolean parse() throws Exception{ //三つ目のメソッド parse＝解析をする

		if(StmtList.isFirst(first)) { //次の判定を開始する
			handler = StmtList.getHandler(first,env);
			handler.parse();
			return true;
		}
		throw new Exception("Programエラーです");//first集合でない時
	}


	public String toString() { //handerが出力するのはこれ
		return   handler.toString() ;
	}
}

