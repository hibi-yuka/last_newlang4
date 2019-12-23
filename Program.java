package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Program extends Node{ //ここに3つのメソッドを書き加えればいい。Nodeは全てのクラスに必要になる

	Environment env;
	Node handler ;

	public Program(Environment env) {//コンストラクタ
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
			LexicalType.DO,
			LexicalType.NL
			);

	public static boolean isFirst(LexicalUnit lu) { //１つ目のメソッド
		return firstSet.contains(lu.getType()); //SetがgetTypeと比較し、要素を保持していたらtrueを返す
	}

	public static Node getHandler(LexicalUnit first, Environment env) { //二つ目のメソッド
		//通ればオブジェクトを作成、通らなければ
		if(StmtList.isFirst(first)) { //isFirstを呼び出す trueならばオブジェクトを作成、そうでなければnullを返す
			return new Program(env); //このProgramインスタンスはNodeとしても扱える。継承先がNode
		}
		return null;
	}

	public boolean parse() throws Exception{ //三つ目のメソッド parse＝解析をする

		LexicalUnit first = env.getInput().get();//getはLexicalAnalyzerImpl。新たにfirstを読み込む

		env.getInput().unget(first);//ungetして読み込んだのを保持する。

		System.out.println(first + " :Program");//一番最初に読み込んだfirstを出力する。テスト

		if(StmtList.isFirst(first) ) { //次の判定を開始する
			handler = StmtList.getHandler(first,env);//スコープについて、ここでNode handlerとするとhandler内部の値はif文{}までしかローカル変数によって保持されない
			return handler.parse();//戻り値としてpaaseを返す事で、数珠繋ぎで返す
		}
		return false;
	}

	public String toString() { //handerが出力するのはこれ

		
		return   handler.toString() ;
	}
}


//型管理をしっかり出来るようにする。

