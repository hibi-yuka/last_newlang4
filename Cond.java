package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Cond extends Node{

	LexicalUnit first;
	Environment env;
	Node handler_left,handler_right;
	LexicalType ope;

	public Cond(LexicalUnit first,Environment env) {//コンストラクタ
		this.first = first;
		this.env = env;
	}

	static final Set<LexicalType> firstSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			//LexicalType.SUB,
			//LexicalType.LP,
			LexicalType.NAME,
			LexicalType.LITERAL
			);

	static final Set<LexicalType> Operatorset = EnumSet.of(
			LexicalType.EQ,
			LexicalType.GT,
			LexicalType.LT,
			LexicalType.GE,
			LexicalType.LE,
			LexicalType.NE
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return firstSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static Node getHandler(LexicalUnit first, Environment env) {
		return new Cond(first,env);
	}

	public boolean parse() throws Exception{

		if(Expr.isFirst(first)) { //式かを判断する
			handler_left = Expr.getHandler(first ,env);
			handler_left.parse();
		}else
			throw new Exception("Condエラーです");

		ope = env.getInput().get().getType();//Typeで判定するから取得

		if(!Operatorset.contains(ope)) {
			throw new Exception("Condエラーです");
		}

		first =  env.getInput().get();//Unitまで再び取得

		if(Expr.isFirst(first)) {
			handler_right = Expr.getHandler(first ,env);
			handler_right.parse();
		}else{
			throw new Exception("Condエラーです");
		}
		return true;
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return "[" + handler_left + "] Operator ["  + handler_right + "]".toString();
	}

	public Value getValue() throws Exception {

		Value left = handler_left.getValue();
		Value right = handler_right.getValue();

		if(left.getType() != left.getType()) {
			throw new Exception("CONDエラーです");
		}

		if(ope == LexicalType.EQ) {

			if(left.getSValue().equals(right.getSValue())) {
				return new ValueImpl("true",ValueType.STRING);
			}else{
				return  new ValueImpl("false",ValueType.STRING);
			}

		}else if(ope == LexicalType.GT) {

			if(left.getDValue() > right.getDValue()) {
				return new ValueImpl("true",ValueType.STRING);
			}else{
				return  new ValueImpl("false",ValueType.STRING);
			}
		}else if(ope == LexicalType.LT) {

			if(left.getDValue() < right.getDValue()) {
				return new ValueImpl("true",ValueType.STRING);
			}else{
				return  new ValueImpl("false",ValueType.STRING);
			}
		}else {
		}
		return null;
	}
}


