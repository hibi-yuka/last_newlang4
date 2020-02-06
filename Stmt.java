package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Stmt extends Node {

	LexicalUnit first;
	Environment env;
	Node handler ;

	public Stmt(LexicalUnit first,Environment env) {
		this.first = first;
		this.env = env;
		type = NodeType.STMT;
	}

	static final Set<LexicalType> firstSet =  EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.NL
			);

	public static boolean isFirst(LexicalUnit lu) {
		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		if(Stmt.isFirst(first)){
			return new Stmt(first,env);
		}
		throw new Exception("EndNodeにないfrst集合です");
	}

	public boolean parse() throws Exception{

		if(End.isFirst(first)){
			handler = End.getHandler(first,env);
			handler.parse();
			return true;
		}else if(For.isFirst(first)){
			handler = End.getHandler(first,env);
			handler.parse();
			return true;
		}

		LexicalUnit secand = env.getInput().get();//二文字目を読み込むにはenvのinputを経由してgetまでいく
		//isfirstの時点でEND,FOR,NAME以外は弾かれるのでsecondの時点でNAMeなのはほぼ確定

		if(secand.getType() == LexicalType.EQ ) {
			env.getInput().unget(secand);// =を読まなかった扱いする為にunget
			handler = Subst.getHandler(first , env);
			handler.parse();
			return true;
		}else{
			env.getInput().unget(secand); // =を読まなかった扱いする為にunget
			handler = Call_Sub.getHandler(first , env);
			handler.parse();
			return true;
		}
	}


	public String toString() {
		return  handler.toString() ;
	}
}
