package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Program extends Node{ //ここに3つのメソッドを書き加えればいい。Nodeは全てのクラスに必要になる

	Environment env;
	Node handler ;

	public Program(Environment env) {//コンストラクタ
		this.env = env;
	}

	//Set<E> E - このセットで保持される要素の型
	static final Set<LexicalType> fristSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO,
			LexicalType.NL
			);

	public static boolean isFirst(LexicalUnit lu) { //１つ目のメソッド
		return fristSet.contains(lu.getType()); //SetがgetTypeと比較し、要素を保持していたらtrueを返す
	}

	public static Node getHandler(LexicalUnit first, Environment env) { //二つ目のメソッド
		//通ればオブジェクトを作成、通らなければ
        if(StmtList.isFirst(first)) { //isFirstを呼び出す trueならばオブジェクトを作成、そうでなければnullを返す
        	return new Program(env); //このProgramインスタンスはNodeとしても扱える。継承先がNode
        }
        return null;
	}

	public boolean parse() throws Exception{ //三つ目のメソッド parse＝解析をする

		//こっちで新たにfirstを読み込む
		LexicalUnit first = env.getInput().get();//getはLexicalAnalyzerImplの奴


		env.getInput().unget(first);//ungetして配列として保管する
		System.out.println(first);

		if(StmtList.isFirst(first) ) { //次の判定を開始する
			handler = StmtList.getHandler(first,env);//スコープについて、ここでNode handlerとするとhandler内部の値はif文{}までしかローカル変数によって保持されない
			return handler.parse();
		}
		return false;
	}

	public String toString() { //handerが出力するのはこれ

		return  handler.toString() ;
	}
		//ここでProgramとStmtListのparseを渡す
}




