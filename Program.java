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
			LexicalType.DO,
			LexicalType.NL
			);

	public static boolean isFirst(LexicalUnit lu) {
		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		if(Program.isFirst(first)){
			return new Program(first,env);
		}
		throw new Exception("Programにないfirst集合です");
	}

	public boolean parse() throws Exception{ //parse＝解析をする

		if(StmtList.isFirst(first)) {
			handler = StmtList.getHandler(first,env);
			handler.parse();
			return true;
		}
		throw new Exception("Programエラーです");
	}

	public String toString() {
		return   handler.toString() ;
	}

	public Value getValue() throws Exception {
		return handler.getValue();
	}
}

