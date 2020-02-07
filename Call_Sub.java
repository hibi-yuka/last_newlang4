package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Call_Sub extends Node{

	LexicalUnit first;
	Environment env;
	Node expr_list ;
	Function Name;

	public Call_Sub(LexicalUnit first,Environment env) {//コンストラクタ
		this.first = first;
		this.env = env;
		type = NodeType.FUNCTION_CALL;
	}

	static final Set<LexicalType> firstSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
			LexicalType.NAME
			);

	public static boolean isFirst(LexicalUnit lu) { //１つ目のメソッド
		return firstSet.contains(lu.getType());
	}

	public static Node getHandler(LexicalUnit first, Environment env) throws Exception {

		if(Call_Sub.isFirst(first)){
			return new Call_Sub(first,env);
		}
		System.out.println(first);
		throw new Exception("Call_Subにないfirst集合です");
	}

	public boolean parse() throws Exception{

		Name = env.getFunction(first.getValue().getSValue());//NAMEを保存
		//LexicalUnitクラス firstのgetValueよりValue型valueよりgetSValue

		first = env.getInput().get();//次を読み込む

		if(ExprList.isFirst(first)) {
			expr_list = ExprList.getHandler(first,env);
			expr_list.parse();
			return true;
		}
		throw new Exception("Call_subエラーです");//first集合でない時
	}

	public String toString() {
		return "["+ Name +"] = ["+ expr_list +"]".toString() ;
	}

	public Value getValue() throws Exception {
		return null;
	}
}

