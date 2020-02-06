package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Loop extends Node{

	LexicalUnit first;
	Environment env;
	Node cond,stmt_list ;

	public Loop(LexicalUnit first,Environment env) {
		this.first = first;
		this.env = env;
		type = NodeType.LOOP_BLOCK;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.DO,
			LexicalType.WHILE
			);

	public static boolean isFirst(LexicalUnit lu) {
		return fristSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		if(Loop.isFirst(first)){
			return new Loop(first,env);
		}
		throw new Exception("LoopNodeにないfrst集合です");
	}

	public boolean parse() throws Exception{

		if(first.getType() == LexicalType.WHILE){ //Whileだった場合
			first = env.getInput().get();

			if(Cond.isFirst(first)) {
				cond = getHandler(first, env);
				cond.parse();
				first = env.getInput().get();
			}else {
				throw new Exception("WHILE[Cond]エラーです");
			}

			if(first.getType() != LexicalType.NL) {
				throw new Exception("WHILE[NL]エラーです");
			}

			first = env.getInput().get();

			if(StmtList.isFirst(first)) {
				stmt_list = getHandler(first, env);
				stmt_list.parse();
				first = env.getInput().get();
			}else {
				throw new Exception("WHILE[stmt_list]エラーです");
			}

			if(first.getType() != LexicalType.WEND) {
				throw new Exception("WHILE[WEND]エラーです");
			}

			first = env.getInput().get();

			if(first.getType() == LexicalType.NL) {
				return true;
			}else {
				throw new Exception("WHILE[NL]エラーです");
			}

		}else if(first.getType() == LexicalType.DO){ //Doだった場合
			first = env.getInput().get();

			if(first.getType() != LexicalType.WHILE || first.getType() != LexicalType.UNTIL) {
				first = env.getInput().get();

				if(Cond.isFirst(first)) {
					cond = getHandler(first, env);
					cond.parse();
					first = env.getInput().get();
				}else {
					throw new Exception("DO[Cond]エラーです");
				}

				if(first.getType() != LexicalType.NL) {
					throw new Exception("DO[NL]エラーです");
				}

				first = env.getInput().get();

				if(StmtList.isFirst(first)) {
					stmt_list = getHandler(first, env);
					stmt_list.parse();
					first = env.getInput().get();
				}else {
					throw new Exception("DO[stmt_list]エラーです");
				}

				if(first.getType() != LexicalType.LOOP) { //LoopNode自体に戻る入れ子構造の可能性がある
					throw new Exception("DO[LOOP]エラーです");
				}

				first = env.getInput().get();

				if(first.getType() == LexicalType.NL) {
					return true;
				}else {
					throw new Exception("DO[NL]エラーです");
				}

			}else if(first.getType() == LexicalType.NL) {

				first = env.getInput().get();

				if(StmtList.isFirst(first)) {
					stmt_list = getHandler(first, env);
					stmt_list.parse();
					first = env.getInput().get();
				}else {
					throw new Exception("DO[stmt_list]エラーです");
				}

				if(first.getType() != LexicalType.LOOP) { //LoopNode自体に戻る入れ子構造の可能性がある
					throw new Exception("DO[LOOP]エラーです");
				}

				first = env.getInput().get();

				if(first.getType() != LexicalType.WHILE || first.getType() != LexicalType.UNTIL) {
					first = env.getInput().get();
				}else {
					throw new Exception("DO[while or until]エラーです");
				}

				if(Cond.isFirst(first)) {
					cond = getHandler(first, env);
					cond.parse();
					first = env.getInput().get();
				}else {
					throw new Exception("DO[Cond]エラーです");
				}

				if(first.getType() == LexicalType.NL) {
					return true;
				}else {
					throw new Exception("DO[NL]エラーです");
				}
			}
		}
		return false;
	}
}





