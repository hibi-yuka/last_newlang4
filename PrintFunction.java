package newlang4;

public class PrintFunction extends Function {

	public PrintFunction() {
	}

	public Value invoke(ExprList arg){//結局これは何を出力する？ExprListの中身をもらう

		try {
			System.out.println(arg.getValue(0).getSValue());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return "PRINT".toString();
	}
}
//ExprList型変数arg
//Printfanctionは引数を出力
//その引数が配列状態
// printf(arg[0]);


