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

		if(first.getType() == LexicalType.NAME) {
			System.out.println(first + " :Subst");
			return true;
		}else {
			return false;
		}
	}

	public String toString() {
		return "subst" + env.input ;//+ handler.toString();

	}
}
//const int リテラル　doubleなどをvalue型で保存する。入れるノードを作る。本当は違うが、syntaxとして動かすだけなら
//Nameも保存する出来る様にする　