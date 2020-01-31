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

	public ExprList(LexicalUnit first,Environment env) {//コンストラクタ
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

	public static boolean isFirst(LexicalUnit lu) { //１つ目のメソッド
		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) { //二つ目のメソッドここでは引数が二つ渡されている。最初に読み込んだ
		return new ExprList(first,env);
	}

	public boolean parse() throws Exception{

		if(Expr.isFirst(first)) {
			handler = Expr.getHandler(first, env);//handlerを持てるリストを作るべし
			handler.parse();
			e_list.add(handler);
		}

		while(true) {

		first = env.getInput().get();//次を読み込む

		if(first.getType() == LexicalType.COMMA) {
			first = env.getInput().get();//次を読み込む
			if(Expr.isFirst(first)) {
				handler = Expr.getHandler(first, env);
				handler.parse();
				e_list.add(handler);
			}else
				break;
			}
		}
		return true;//無限ループが起こった場合はここが悪い可能性がある
	}

	public String toString() {
		return e_list.toString();
	}
}

//	if(first.getType() == LexicalType.RP) { )がきた時点で抜ける
