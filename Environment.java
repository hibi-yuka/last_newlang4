package newlang4;

import java.util.Hashtable;
import java.util.Map;

public class Environment {
	   LexicalAnalyzer input;
	   Map<String, Function> library;//関数名及び、その関数の中身のmap
	   Map<String, VariableNode> var_table;

	    public Environment(LexicalAnalyzer my_input) {
	        input = my_input;
	        library = new Hashtable();
	        library.put("PRINT", new PrintFunction() );
	        var_table = new Hashtable();//初期化しただけ
	    }

	    public LexicalAnalyzer getInput() {
	        return input;
	    }
	    public Function getFunction(String fname) {
	        return (Function) library.get(fname);
	    }

	    public VariableNode getVariable(String vname) {
	        VariableNode v;
	        v = (VariableNode) var_table.get(vname);//vnameを引数に渡すと、VariableNodeを返す
	        if (v == null) {//指定されたキーが存在しない時null、なかったら取り合えず登録
	            v = new VariableNode(vname);//
	            var_table.put(vname, v);//後で変数名と値のペアを登録しておく
	        }
	        return v;//二回目の呼び出しなら
	    }
}
