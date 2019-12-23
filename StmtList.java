package newlang4;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class StmtList extends Node {

	Environment env;
	Node handler;
	public List<Node> handlerlist = new ArrayList<Node>();

	public StmtList(Environment env) {//コンストラクタ
		this.env = env;
		type = NodeType.STMT_LIST;
	}

	static final Set<LexicalType> fristSet =  EnumSet.of(
			LexicalType.NAME,
			LexicalType.FOR,
			LexicalType.END,
			LexicalType.IF,
			LexicalType.WHILE,
			LexicalType.DO,
			LexicalType.NL
			);

	public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
		return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
	}

	public static StmtList getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
		return new StmtList(env);//StmtListクラスをインスタンス化する
	}

	public boolean parse() throws Exception{//ここでツリーを作る

		while(true) { //stmtである限り繰り返す
			LexicalUnit first = env.getInput().get();

			if(first.getType() == LexicalType.NL) {//NLを読み飛ばす作業
				continue; //処理を抜けて再び上に戻る
			}else {
				env.getInput().unget(first);
			}


			System.out.println(first + " :Stmt_list");//出力テスト

			if(Stmt.isFirst(first)) {
				handler = Stmt.getHandler(first , env);//first集合がstmtだったら
				System.out.println(first + " :Stmt");//出力テスト
				handler.parse();
				handlerlist.add(handler);//
			}else if(Block.isFirst(first)) {
				handler = Block.getHandler(first, env);
				System.out.println(first + " :Block1");//出力テスト
				handler.parse();
				handlerlist.add(handler);//listとする
			}else {
			break;
			}
		}
		return false;
	}

//while文、上から実行された時に、if文の結果に関係なく、最後のreturn flaseが実行されている
//いくらもリストにならない。breakをいれる
	//処理に困ったらbreak
//54と60でreturnするとだめ
	public String toString() {
		return    handler.toString();
	}
}

