package newlang4;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class StmtList extends Node {

	Environment env;
	Node handler;
	 public List<Node> handlerlist = new ArrayList<Node>();

	public StmtList(Environment env) {
		this.env = env;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO,
			LexicalType.NL
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static StmtList getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new StmtList(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{

		//ここでツリーを作る
	while(true) { //stmtである限り繰り返す
		LexicalUnit first = env.getInput().get();
		env.getInput().unget(first);
		//System.out.println(first);
	    if(Stmt.isFirst(first)) {
			handler = Stmt.getHandler(first , env);
			handler.parse();
			handlerlist.add(handler);
	    }
	    	return true;//IOF、ELSEなどが来た時に抜ける
	    }
	}


	public String toString() {
		return "Stmt_List" + "" +  handlerlist.toString();
	}
}

