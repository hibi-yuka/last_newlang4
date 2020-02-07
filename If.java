package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class If extends Node {

	LexicalUnit first;
	Environment env;
	Node cond,stmt_list1,elseif;


	public If(LexicalUnit first,Environment env) {
		this.first = first;
		this.env = env;
		type = NodeType.IF_BLOCK;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.IF,
			LexicalType.ELSEIF
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

		if(first.getType() == LexicalType.IF || first.getType() == LexicalType.ELSEIF) {
			first = env.getInput().get();
		}else {
			throw new Exception("If or Elseifエラーです");
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
			stmt_list1 = StmtList.getHandler(first, env);
			stmt_list1.parse();
		}

		//elseの場合、else ifの場合、なにもない場合
		if(first.getType() == LexicalType.ELSEIF) {
			elseif = If.getHandler(first, env);
			elseif.parse();
			return true;

		}else if(first.getType() == LexicalType.ELSE) {

			first = env.getInput().get();//次を読み込みNLか調べる

			if(first.getType() != LexicalType.NL) {//NLでないならエラー、NLなら通る
				throw new Exception("Ifエラーです");
			}

			if(StmtList.isFirst(first)) {
				elseif = StmtList.getHandler(first, env);
				elseif.parse();
			}

			if(first.getType() != LexicalType.ENDIF) {
				throw new Exception("Ifエラーです");
			}

		}else if(first.getType() != LexicalType.ENDIF)
			throw new Exception("Ifエラーです");

		first = env.getInput().get();

		if(first.getType() != LexicalType.NL) {
			throw new Exception("Ifエラーです");
		}
		return true;
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return  "["+cond+"] ["+ stmt_list1 +"]["+ elseif +"]".toString();
	}

	public Value getValue() throws Exception {

		if(cond.getValue().getSValue() == "ture") {
			return new ValueImpl("true",ValueType.STRING);
		}else{
			return  new ValueImpl("false",ValueType.STRING);
		}

		//return null;
	}

}
