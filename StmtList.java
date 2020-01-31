package newlang4;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class StmtList extends Node {

	LexicalUnit first;
	Environment env;
	Node handler ;
	List<Node>s_list  = new ArrayList<Node>();

	public StmtList (LexicalUnit first,Environment env) {//コンストラクタ
		this.first = first;
		this.env = env;
		type = NodeType.STMT_LIST;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO
			);

	public static boolean isFirst(LexicalUnit lu) {
		return fristSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception { //ここでは引数が二つ渡されている。最初に読み込んだ

		if(StmtList.isFirst(first)){ //first集合を比べて、大丈夫ならPrgramインスタンスが生成される
			return new StmtList(first,env);
		}
		throw new Exception("StmtListにないfrst集合です");//first集合でない時
	}

	public boolean parse() throws Exception{//ここでツリーを作る

		while(true) {
		if(Stmt.isFirst(first)) { //次の判定を開始する
			handler = Stmt.getHandler(first,env);
			handler.parse();
			s_list.add(handler);
			first = env.getInput().get();
			}else {
				break;
			}
		}
		return true;
	}

//	throw new Exception("StmtListエラーです");//first集合でない時

	public String toString() {
		return  s_list.toString() ;
	}
}
