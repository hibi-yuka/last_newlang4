package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class If_prefix extends Node{

	Environment env;
	Node handler;

	public If_prefix(Environment env) {
		this.env = env;
	}

	//Set<E> E - このセットで保持される要素の型
		static final Set<LexicalType> fristSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
				LexicalType.IF
				);

		public static boolean isFirst(LexicalUnit lu) { //１つ目のメソッド
			return fristSet.contains(lu.getType()); //Setが指定された要素を保持している場合にtrue
		}

		public static If_prefix getHandler(LexicalUnit first, Environment env) { //二つ目のメソッドここでは引数が二つ渡されている。最初に読み込んだ
			return new If_prefix(env);
		}

		public boolean parse() throws Exception{ //三つ目のメソッド

			LexicalUnit first = env.getInput().get();
			env.getInput().unget(first);


			if(first.getType()== LexicalType.IF) {
				return true;
			}else{
			System.exit(-1);
			return false;
			}

		}

	public String toString() {
		return "IF";
	}
}




