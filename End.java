package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class End extends Node{

	Environment env;

	static final Set<LexicalType> fristSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
			LexicalType.END
			);

	public End(Environment env){
		this.env = env;
		type = NodeType.END;
	}

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static End getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new End(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{//ENDではparseどうする？普通ならここでENDなので

		LexicalUnit first = env.getInput().get();

		if(End.isFirst(first)) {
			System.out.println(first + " : finalEnd");
			return true;
			}
		return false;
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