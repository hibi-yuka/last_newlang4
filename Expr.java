package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Expr extends Node{

	LexicalUnit first;
	Environment env;
	Node handler_left,handler_light,handler;
	LexicalType ope;

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

		if(Const.isFirst(first)) {//Name以外が来たら 左辺
			handler = Const.getHandler(first, env);
			handler.parse();
			//return true;
		}else if(first.getType() == LexicalType.NAME) {//NAMEが来たら
			handler = env.getVariable(first.getValue().getSValue());
		}else {
			throw new Exception("Exprエラー");//ここで例外処理
		}

		first = env.getInput().get();//ここで次の文字を読む

		if(Operatorset.contains(first.getType())) {
			ope = first.getType();//opeに保存する

			first = env.getInput().get();//更に次の読む

			if(Const.isFirst(first)) {//Name以外が来たら 右辺スタート
				handler = Const.getHandler(first, env);
				handler.parse();
				return true;

			}else if(first.getType() == LexicalType.NAME) {//NAMEが来たら
				handler = env.getVariable(first.getValue().getSValue());
				return true;
			}else {
				throw new Exception("Exprエラー");//ここで例外処理
			}

		}else {
			env.getInput().unget(first);
			return true; // a = 1のようなパターンあり
		}
	}

	public String toString() {

		if(ope == LexicalType.ADD) {
			return "["+handler_left+"]" + "+" + "["+handler_light+"]".toString();
		}
		return handler.toString();
	}

	public Value getValue() throws Exception {
		return handler.getValue();
	}
}
