package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Var extends Node{

	//Set<E> E - このセットで保持される要素の型
		static final Set<LexicalType> fristSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します
				LexicalType.NAME

				);


		public static boolean isFirst(LexicalUnit lu) { //１つ目のメソッド
			return fristSet.contains(lu.getType()); //Setが指定された要素を保持している場合にtrue
		}


		public static Node getHandler(LexicalUnit first, Environment env) { //二つ目のメソッドここでは引数が二つ渡されている。最初に読み込んだ
			return null;
		}

		public boolean parse() throws Exception{ //三つ目のメソッド

			if(StmtList.isFirst(first)) {
				Node handler = StmtList.getHandler(first,env);//
				handler.parse();
			}
			return true;
		}

	}


}
