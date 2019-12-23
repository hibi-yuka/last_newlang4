package newlang4;

import java.util.Hashtable;

public class Environment {
	   LexicalAnalyzer input;
	   Hashtable var_table;

	    public Environment(LexicalAnalyzer my_input) {//正確にはLexicalAnalyzerImplクラスの引数をinputへ代入する
	        input = my_input;
	        var_table = new Hashtable();
	    }

	    public LexicalAnalyzer getInput() { //LexicalAnalyzerを呼び出せるようにおいておくクラス
	        return input;
	    }
}
