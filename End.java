package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class End extends Node{

	LexicalUnit first;
	Environment env;
	Node handler ;

	public End(LexicalUnit first,Environment env){
		this.first = first;
		this.env = env;
		type = NodeType.END;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.END
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static End getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new End(first,env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{//ENDではparseどうする？普通ならここでENDなので
		//一番下なしは多くの場合は内容を保存,ENDだけは例外
		return true;
	}

	public String toString() {
		if (type == NodeType.END) {
			return "END";
		}else{
				System.out.println(type);
		return "Node";
		}
	}
}
//Stmt_list処理可能か→true→stmt_list→またend →　stmtに