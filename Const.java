package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Const extends Node{

		Environment env;
		Node handler;
		Value value;

		public Const(Environment env) {//コンストラクタ
			this.env = env;
		}

		static final Set<LexicalType> fristSet =  EnumSet.of(
				LexicalType.INTVAL,
				LexicalType.DOUBLEVAL,
				LexicalType.LITERAL,
				LexicalType.NAME
				);

		public static boolean isFirst(LexicalUnit lu) {//isFistメソッドでlu
			return fristSet.contains(lu.getType()); //リストが特定の要素を含むか判定
		}

		public static Const getHandler(LexicalUnit first, Environment env) { //ここでは引数が二つ渡されている。最初に読み込んだ
			return new Const(env);//StmtListクラスをインスタンス化する
		}

		public boolean parse() throws Exception{//ここでツリーを作る

				LexicalUnit first = env.getInput().get();
				env.getInput().unget(first);

				value = first.getValue();
				System.out.println(first.getValue() + " :Const");//出力テスト
				return true;
		}

		public String toString() {
			return handler.toString();
		}
	}

//const int literal doubleなどをvalue型で保存する。入れるノードを作る。本当は違うが、syntaxとして動かすだけなら
//Nameも保存する出来る様にする


