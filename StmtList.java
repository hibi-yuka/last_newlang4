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

	static final Set<LexicalType> firstSet =  EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO,
			LexicalType.NL
			);

	public static boolean isFirst(LexicalUnit lu) {
		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		if(StmtList.isFirst(first)){
			return new StmtList(first,env);
		}
		throw new Exception("StmtListにないfrst集合です");//first集合でない時
	}

	public boolean parse() throws Exception{

		while(true) {

			if(first.getType() != LexicalType.NL) {
				if(Stmt.isFirst(first)) { //次の判定を開始する
					handler = Stmt.getHandler(first,env);
					handler.parse();
					s_list.add(handler);
					first = env.getInput().get();
				}else if(Block.isFirst(first)) {
					handler = Block.getHandler(first,env);
					handler.parse();
					s_list.add(handler);
					first = env.getInput().get();

				}else {
					env.getInput().unget(first);
					break;
				}
			}else {
				first = env.getInput().get();
			}
		}
		return true;
	}

	public String toString() {
		return  s_list.toString() ;
	}

	public Value getValue() throws Exception {

		for(int i =0; i < s_list.size(); i++) {
		 s_list.get(i).getValue();
		}
		return null;
	}
}

