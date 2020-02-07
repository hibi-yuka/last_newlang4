package newlang4;

import java.util.Hashtable;
import java.util.Map;

public class Environment {
	LexicalAnalyzer input;//れきしかるあならいざーをもっている。まいかいむだにな
	Map<String, Function> library;//関数名及び、その関数の中身のmap
	Map<String, VariableNode> var_table;//変数名と変数の実体。ここで管理して

	public Environment(LexicalAnalyzer my_input) {//ここでもらったものを保存する
		input = my_input;
		library = new Hashtable();//新しくハッシュテーブルを作る。
		library.put("PRINT", new PrintFunction() );//ここでPRINT文を登録する
		var_table = new Hashtable();//初期化しただけ//初期化する
	}

	public LexicalAnalyzer getInput() {//持っている者を返すだけ
		return input;
	}
	public Function getFunction(String fname) {//nullの下り
		return (Function) library.get(fname);//libraryに登録されたfnameから値を返す
	}

	public VariableNode getVariable(String vname) {//値を登録
		VariableNode v;//宣言するだけ
		v = (VariableNode) var_table.get(vname);//vnameを引数に渡すと、VariableNodeを返す
		if (v == null) {//指定されたキーが存在しない時null、なかったら取り合えず登録
			v = new VariableNode(vname);//新しいノードを作る。
			var_table.put(vname, v);//作った奴を変数名と値のペアを登録しておく
		}
		return v;//二回目の呼び出しなら
	}
}
