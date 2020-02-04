package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class If extends Node {

	LexicalUnit first;
	Environment env;
	Node cond,stmt_list,else_block;


	public If(LexicalUnit first,Environment env) {
		this.first = first;
		this.env = env;
		type = NodeType.IF_BLOCK;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.IF
			);

	public static boolean isFirst(LexicalUnit lu) {
		return fristSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		if(If.isFirst(first)){
		 return new If(first,env);
		}
		throw new Exception("Ifにはないfirst集合です");
	}

	public boolean parse() throws Exception{

		if(first.getType() == LexicalType.IF) {
			first = env.getInput().get();
		}else {
			throw new Exception("If_prefixエラーです");
		}

		if(Cond.isFirst(first)) {
			cond = Cond.getHandler(first,env);
			cond.parse();
			first = env.getInput().get();
		}else {
			throw new Exception("Ifエラーです");
		}

		if(first.getType() != LexicalType.THEN) {
			throw new Exception("Ifエラーです");
		}

		first = env.getInput().get();

		if(first.getType() != LexicalType.NL) {
			throw new Exception("Ifエラーです");
		}

		first = env.getInput().get();

		if(StmtList.isFirst(first)) {
			stmt_list = StmtList.getHandler(first, env);
			stmt_list.parse();
		}

		if(Else_Block.isFirst(first)) {
			else_block = Else_Block.getHandler(first, env);
			else_block.parse();
		}

		if(first.getType() != LexicalType.ENDIF) {
			throw new Exception("Ifエラーです");
		}

		if(first.getType() != LexicalType.NL) {
			throw new Exception("Ifエラーです");
		}
		return true;
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return handler.toString();
	}
}
