package newlang4;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ExprList extends Node{

	LexicalUnit first;
	Environment env;
	Node handler;
	List<Node> e_list = new ArrayList<Node>();

	public ExprList(LexicalUnit first,Environment env) {
		this.first = first;
		this.env = env;
	}

	static final Set<LexicalType> firstSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
			LexicalType.INTVAL,
			LexicalType.DOUBLEVAL,
			//LexicalType.SUB,
			LexicalType.LP,
			LexicalType.NAME,
			LexicalType.LITERAL
			);

	public static boolean isFirst(LexicalUnit lu) {
		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) {
		return new ExprList(first,env);
	}

	public boolean parse() throws Exception{

		if(first.getType() == LexicalType.LP) {
			first = env.getInput().get();
		}

		if(Expr.isFirst(first)) {
			handler = Expr.getHandler(first, env);
			handler.parse();
			e_list.add(handler);
		}

		while(true) {

			first = env.getInput().get();//次を読み込む

			if(first.getType() == LexicalType.COMMA) {//コンマだったら中の処理、そうでないならbreak
				first = env.getInput().get();//次を読み込む
				if(Expr.isFirst(first)) {

					handler = Expr.getHandler(first, env);
					handler.parse();
					e_list.add(handler);
				}else {
					throw new Exception("ExprListにないfrst集合です");
				}//unget入れとく、でないと一番外のif文でない時に読み込んだ値を捨てている
			}
			//env.getInput().unget(first);
			break;
		}

		if(first.getType() != LexicalType.RP) {
			env.getInput().unget(first);
			//throw new Exception("Expr[RP]エラーです");
		}
		return true;
	}

	public String toString() {
		return e_list.toString();
	}

	public Value getValue() throws Exception {
		return null;
	}

	public Value getValue(int n) throws Exception {

		return  e_list.get(n).getValue();//任意のインデックスの引数のgetvalueの結果を返す、ExprListには"hello"などが入っているからそれを取り出していく

	}

}


