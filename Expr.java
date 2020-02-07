package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Expr extends Node{

	LexicalUnit first;
	Environment env;
	Node handler_left,handler_light,handler;
	LexicalType operator;

	public Expr (LexicalUnit first,Environment env) {//コンストラクタ
		this.first = first;
		this.env = env;
		type = NodeType.EXPR;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			LexicalType.SUB,
			LexicalType.LP,
			LexicalType.NAME,
			LexicalType.LITERAL
			);

	static final Set<LexicalType> Operatorset = EnumSet.of(
			LexicalType.ADD,
			LexicalType.SUB
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {
		return new Expr(first,env);
	}

	public boolean parse() throws Exception{

		if(Const.isFirst(first)) {//Name以外が来たら
			handler = Const.getHandler(first, env);
			handler.parse();
			//return true;
			first = env.getInput().get();

		}else if(Operatorset.contains(operator)) {


		}
/*
		}else if(Expr.isFirst(first)) {
			handler_left = Expr.getHandler(first, env);
			handler_left.parse();

			first = env.getInput().get();

			if(first.getType() == LexicalType.ADD) {
				operator = env.getInput().get().getType();
			}else {
				throw new Exception("エラー");
			}

			first =  env.getInput().get();

			if(Expr.isFirst(first)) {
				handler_light = Expr.getHandler(first, env);
				handler_light.parse();
				return true;
			}
		}
*/
		//Constのfirst集合(NAME以外)があるならConstに投げる elseで下(値はfirstの中に入っている。firstを渡す)
		handler = env.getVariable(first.getValue().getSValue());//ここに値を保存する firstがNAMEの時
		return true;
	}

	public String toString() {

		if(operator == LexicalType.ADD) {
			return "["+handler_left+"]" + operator + "["+handler_light+"]".toString();
		}
		return handler.toString();
	}

	public Value getValue() throws Exception {
		return handler.getValue();
	}
}
