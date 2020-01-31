package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Stmt extends Node {

	LexicalUnit first;
	Environment env;
	Node handler ;

	public Stmt(LexicalUnit first,Environment env) {
		this.first = first;
		this.env = env;
		type = NodeType.STMT;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception { //ここでは引数が二つ渡されている。最初に読み込んだ

		if(Stmt.isFirst(first)){ //first集合を比べて、大丈夫ならPrgramインスタンスが生成される
			return new Stmt(first,env);
		}
		throw new Exception("EndNodeにないfrst集合です");//first集合でない時
	}

	public boolean parse() throws Exception{

		if(End.isFirst(first)){ //次の判定を開始する
			handler = End.getHandler(first,env);
			handler.parse();
			return true;
		}

		LexicalUnit secand = env.getInput().get();//二文字目を読み込むにはenvのinputを経由してgetまでいく

		if(secand.getType() == LexicalType.EQ ) {
			env.getInput().unget(secand);//読み込んだ文字を読まなかった事にする
			handler = Subst.getHandler(first , env);
			handler.parse();
			return true;
		}else{
			env.getInput().unget(secand);//読み込んだ文字を読まなかった事にする
			handler = Call_Sub.getHandler(first , env);
			handler.parse();
			return true;
		}
	}
	//throw new Exception("Stmtエラーです");//first集合でない時


	public String toString() {
		return  handler.toString() ;
	}
}
