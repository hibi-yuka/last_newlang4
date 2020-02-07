package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Block extends Node {

	LexicalUnit first;
	Environment env;
	Node handler ;

	public Block(LexicalUnit first,Environment env) {
		this.first = first;
		this.env = env;
		type = NodeType.STMT;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.IF,
			LexicalType.DO,
			LexicalType.WHILE
			);

	public static boolean isFirst(LexicalUnit lu) {
		return fristSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		if(Block.isFirst(first)){
			return new Block(first,env);
		}
		throw new Exception("Blockにないfrst集合です");
	}

	public boolean parse() throws Exception{

		if(If.isFirst(first)) {
			handler = If.getHandler(first,env);
			handler.parse();
		}else if(Loop.isFirst(first)) {
			handler = Loop.getHandler(first,env);
			handler.parse();
		}else {
			throw new Exception("Blockエラーです");
		}
		return true;
	}

	public String toString() {

		return handler.toString();
	}

	public Value getValue() throws Exception {
		return handler.getValue();
	}
}




