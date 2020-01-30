package newlang4;

import java.util.Hashtable;

public class Environment {
	   LexicalAnalyzer input; //LexicalAnalayzerクラスのinput変数
	   Hashtable var_table; //Hashtableクラスのvar_table変数

	    public Environment(LexicalAnalyzer my_input) {//正確にはLexicalAnalyzerImplクラスの引数をinputへ代入する
	        input = my_input;//読み込んだ文字のLexicalAnalyzerに入っている情報がinputへ渡される
	        var_table = new Hashtable();//Hashtableクラスのインスタンスを生成
	    }

	    public LexicalAnalyzer getInput() { //LexicalAnalyzerを呼び出せるようにおいておくクラス
	        return input;
	    }
}
