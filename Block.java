package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Block extends Node {

	Environment env;
	Node handler;

	public Block(Environment env) {
		this.env = env;
	}

	//Set<E> E - このセットで保持される要素の型
		static final Set<LexicalType> fristSet =  EnumSet.of( // EnumSet.of(E e)=指定された要素を最初に含む enum セットを作成します

				LexicalType.IF,
				LexicalType.WHILE,
				LexicalType.DO
				);


		public static boolean isFirst(LexicalUnit lu) { //１つ目のメソッド
			return fristSet.contains(lu.getType()); //Setが指定された要素を保持している場合にtrue
		}


		public static Block getHandler(LexicalUnit first, Environment env) { //二つ目のメソッドここでは引数が二つ渡されている。最初に読み込んだ
			return new Block(env);
		}

		public boolean parse() throws Exception{ //三つ目のメソッド

			LexicalUnit first = env.getInput().get();
			env.getInput().unget(first);

			if(first.getType() == LexicalType.IF || first.getType() == LexicalType.WHILE || first.getType() == LexicalType.DO ) {
				return true;
			}else {
				System.exit(-1);
				return false;
			}
		}

			//if(End.isFirst(first)) {
			//	handler = End.getHandler(first,env);//
			//System.out.println("Block2" + first);
				//return handler.parse();
		//	}
		//	return false;
	//	}

		public String toString() {
			return "DO" + "WHILE" + "IF";//+handler.toString();
		}

	}



