package newlang4;

public class ValueImpl extends Value{

		String val; //フィールドでString型のval
		ValueType vat;//クラス型であるValueType型のvatは文字列や数値が入る

          public ValueImpl(String s) { //n字句目の文字列を入れたい。
    		super(s);
			 val = s;
			 vat = ValueType.BOOL;
		 }

		 public ValueImpl(int i) {
			 super(i);
		 }

		 public ValueImpl(double d) {
			 super(d);
		 }


		 public ValueImpl(boolean b) {
			 super(b);
		 }

		 public ValueImpl(String s, ValueType t) {
			 super(s, t);
			 this.vat = t;
			 this.val = s;

		 }

		 //ここまでvalueから継承したコンストラクタ一覧

		@Override
		public String getSValue() {
			// TODO 自動生成されたメソッド・スタブ
			return this.val;
		}

		@Override
		public int getIValue() {
			// TODO 自動生成されたメソッド・スタブ
			return Integer.parseInt(this.val);
		}

		@Override
		public double getDValue() {
			// TODO 自動生成されたメソッド・スタブ
			return Double.parseDouble(this.val);
		}

		@Override
		public boolean getBValue() {
			// TODO 自動生成されたメソッド・スタブ
			return Boolean.valueOf(this.val);
		}

		@Override
		public ValueType getType() {
			// TODO 自動生成されたメソッド・スタブ
			return this.vat;
		};

	}






