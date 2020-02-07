package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Subst extends Node{


	LexicalUnit first;
	Environment env;
	Node handler_left,expr;


	public Subst (LexicalUnit first,Environment env) {
		this.first = first;
		this.env = env;
		type = NodeType.ASSIGN_STMT;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME
			);

	public static boolean isFirst(LexicalUnit lu) {
		return fristSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {
		return new Subst(first,env);
	}

	public boolean parse() throws Exception{

		handler_left = env.getVariable(first.getValue().getSValue());

		first = env.getInput().get();//getはLexicalAnalyzerImplの奴

		if(first.getType() != LexicalType.EQ) {//=でないならexitで終了
			System.out.println("EQではありません");
			System.exit(1);
		}

		first = env.getInput().get(); //  前のが=だった時、次の文字をgetして読み込む

		if(Expr.isFirst(first)) { //式かを判断する
			expr = Expr.getHandler(first ,env);
			expr.parse();
			return true;

		}
		System.out.println("解析エラー");
		System.exit(1);
		return false;//エラー発生時にexitで抜けるが、falseがないとコンパイルで引っかかる
	}

	public String toString() {
		return "[" + handler_left +"] = [" + expr + "] ".toString(); //handler2も追加すべし
	}

	public Value getValue() throws Exception {
		return handler_left;
	}

}
