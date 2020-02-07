package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Expr extends Node{

	LexicalUnit first;
	Environment env;
	Node handler_left,handler_light,handler;
	LexicalType ope = null;

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
			handler_left = Const.getHandler(first, env);
			handler_left.parse();
			//return true;
		}else if(first.getType() == LexicalType.NAME) {//NAMEが来たら
			handler_left = env.getVariable(first.getValue().getSValue());
		}else {
			throw new Exception("Exprエラー");//ここで例外処理
		}

		first = env.getInput().get();//ここで次の文字を読む

		if(Operatorset.contains(first.getType())) {
			ope = first.getType();//opeに保存する

			first = env.getInput().get();//更に次の読む

			if(Const.isFirst(first)) {//Name以外が来たら 右辺スタート
				handler_light = Const.getHandler(first, env);
				handler_light.parse();
				return true;

			}else if(first.getType() == LexicalType.NAME) {//NAMEが来たら
				handler_light = env.getVariable(first.getValue().getSValue());
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
		}else if(ope == LexicalType.SUB) {
			return "["+handler_left+"]" + "-" + "["+handler_light+"]".toString();
		}
		return handler_left.toString();
	}
//getHandlerするものはungetがいる
	public Value getValue() throws Exception {

		Value left = handler_left.getValue();

		if( ope == null) { //a =2 などの時
			return handler_left.getValue();
		}

	    Value light = handler_light.getValue();

		if(ope == LexicalType.ADD) {

			if(left.getType() == ValueType.INTEGER && light.getType() == ValueType.INTEGER) {

				int int_value = left.getIValue() + light.getIValue();

				return new ValueImpl(int_value + "",ValueType.INTEGER);

			}else if(left.getType() == ValueType.STRING || light.getType() == ValueType.STRING) {

				String string_value =left.getSValue() + light.getSValue();

				return new ValueImpl(string_value,ValueType.STRING);
			}else {

				double double_value =left.getDValue() + light.getDValue();

				return new ValueImpl(double_value + "",ValueType.DOUBLE);

			}

		}else if(ope == LexicalType.SUB) {

			if(left.getType() == ValueType.INTEGER && light.getType() == ValueType.INTEGER) {

				int int_value =	left.getIValue() - light.getIValue();

				return new ValueImpl(int_value + "",ValueType.INTEGER);

			}else if(left.getType() == ValueType.STRING || light.getType() == ValueType.STRING) {

				return new ValueImpl("エラー" ,ValueType.STRING);
			}else {

				double double_value =left.getDValue() - light.getDValue();

				return new ValueImpl(double_value + "",ValueType.DOUBLE);

			}
		}
		return null;

	}
}
