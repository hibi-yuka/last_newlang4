package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class If_prefix extends Node {

	LexicalUnit first;
	Environment env;
	Node handler ;


	public If_prefix(LexicalUnit first,Environment env) {
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

		if(If_prefix.isFirst(first)){
		 return new If_prefix(first,env);
		}
		throw new Exception("If_prexにはないfirst集合です");
	}

	public boolean parse() throws Exception{

		if(first.getType() == LexicalType.IF) {
			first = env.getInput().get();
		}

		if(Cond.isFirst(first)) {
			handler = Cond.getHandler(first,env);
			handler.parse();
			first = env.getInput().get();
		}

		if(first.getType() == LexicalType.THEN) {
			return true;
		}
		throw new Exception("IF_prefixエラーです");//first集合でない時
	}
}
