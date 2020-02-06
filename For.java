package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class For extends Node {

	LexicalUnit first;
	Environment env;
	Node subst,stmt_list ;
	Function Name;

	public For(LexicalUnit first,Environment env) {
		this.first = first;
		this.env = env;
		type = NodeType.FOR_STMT;
	}

	static final Set<LexicalType> firstSet =  EnumSet.of(
			LexicalType.FOR
			);

	public static boolean isFirst(LexicalUnit lu) {
		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		if(For.isFirst(first)){
			return new For(first,env);
		}
		throw new Exception("ForNodeにないfrst集合です");
	}

	public boolean parse() throws Exception{

		if(For.isFirst(first)){
			first = env.getInput().get();
		}else {
			throw new Exception("For[FOR]エラーです");
		}

		if(Subst.isFirst(first)) {
			subst = Subst.getHandler(first, env);
			subst.parse();
			first = env.getInput().get();
		}else {
			throw new Exception("For[Subst]エラーです");
		}

		if(first.getType() == LexicalType.TO) {
			first = env.getInput().get();
		}else {
			throw new Exception("For[TO]エラーです");
		}

		if(first.getType() == LexicalType.NL) {
			first = env.getInput().get();
		}else {
			throw new Exception("For[NL]エラーです");
		}

		if(StmtList.isFirst(first)) {
			stmt_list = StmtList.getHandler(first, env);
			stmt_list.parse();
			first = env.getInput().get();
		}else {
			throw new Exception("For[StmtList]エラーです");
		}

		if(first.getType() == LexicalType.NEXT) {
			first = env.getInput().get();
		}else {
			throw new Exception("For[NEXT]エラーです");
		}

		if(first.getType() == LexicalType.NAME) { //正規ならここまでくる
			Name = env.getFunction(first.getValue().getSValue());//NAMEを保存
			return true;
		}else {
			throw new Exception("For[Name]エラーです");
		}
	}
}