package newlang4;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LexicalAnalyzerImpl implements LexicalAnalyzer {

	PushbackReader word;
	int nextword;
	String input;
	private static Map<String, LexicalUnit> Reservation = new HashMap<String, LexicalUnit>();
	private static Map<String, LexicalUnit> operator = new HashMap<String, LexicalUnit>();
    public List<LexicalUnit> list = new ArrayList<LexicalUnit>();

	static {
		Reservation.put("IF", new LexicalUnit(LexicalType.IF));
		Reservation.put("THEN", new LexicalUnit(LexicalType.THEN));
		Reservation.put("ELSE", new LexicalUnit(LexicalType.ELSE));
		Reservation.put("ELSEIF", new LexicalUnit(LexicalType.ELSEIF));
		Reservation.put("ENDIF", new LexicalUnit(LexicalType.ENDIF));
		Reservation.put("FOR", new LexicalUnit(LexicalType.FOR));
		Reservation.put("FORALL", new LexicalUnit(LexicalType.FORALL));
		Reservation.put("NEXT", new LexicalUnit(LexicalType.NEXT));
		Reservation.put("SUB", new LexicalUnit(LexicalType.SUB));
		Reservation.put("FUNC", new LexicalUnit(LexicalType.FUNC));
		Reservation.put("DIM", new LexicalUnit(LexicalType.DIM));
		Reservation.put("AS", new LexicalUnit(LexicalType.AS));
		Reservation.put("END", new LexicalUnit(LexicalType.END));
		Reservation.put("DOT", new LexicalUnit(LexicalType.DOT));
		Reservation.put("WHILE", new LexicalUnit(LexicalType.WHILE));
		Reservation.put("DO", new LexicalUnit(LexicalType.DO));
		Reservation.put("UNTIL", new LexicalUnit(LexicalType.UNTIL));
		Reservation.put("LOOP", new LexicalUnit(LexicalType.LOOP));
		Reservation.put("TO", new LexicalUnit(LexicalType.TO));
		Reservation.put("WEND", new LexicalUnit(LexicalType.WEND));
	//	Reservation.put("EOF", new LexicalUnit(LexicalType.EOF));
		operator.put("=", new LexicalUnit(LexicalType.EQ));
		operator.put("<", new LexicalUnit(LexicalType.LT));
		operator.put(">", new LexicalUnit(LexicalType.GT));
		operator.put("<=", new LexicalUnit(LexicalType.LE));
		operator.put("=<", new LexicalUnit(LexicalType.LE));
		operator.put(">=", new LexicalUnit(LexicalType.GE));
		operator.put("=>", new LexicalUnit(LexicalType.GE));
		operator.put("<>", new LexicalUnit(LexicalType.NE));
		operator.put("+", new LexicalUnit(LexicalType.ADD));
		operator.put("-", new LexicalUnit(LexicalType.SUB));
		operator.put("*", new LexicalUnit(LexicalType.MUL));
		operator.put("/", new LexicalUnit(LexicalType.DIV));
		operator.put(")", new LexicalUnit(LexicalType.LP));
		operator.put("(", new LexicalUnit(LexicalType.RP));
		operator.put(",", new LexicalUnit(LexicalType.COMMA));
		operator.put("\n", new LexicalUnit(LexicalType.NL));
		operator.put("\r", new LexicalUnit(LexicalType.NL));
		operator.put("\r\n", new LexicalUnit(LexicalType.NL));
	}

	public LexicalAnalyzerImpl(PushbackReader pr) {
		this.word = pr;
	}

	@Override
	public LexicalUnit get() throws Exception {

		if(!list.isEmpty()) {
			LexicalUnit List_UNIT = list.get(list.size()-1); //次を読むか、ungetを読むか
			list.remove(list.size()-1);
			return List_UNIT;
		}

		int inword = word.read();
		String first = (char) inword + "";
		while (first.equals(" ") || first.equals("\t")) {
			inword = word.read();
			first = (char) inword + "";
		}
		if (inword == (char)-1) {
			return new LexicalUnit(LexicalType.EOF);
		} else if (first.matches("[a-zA-Z]")) {
			return getName(first);
		} else if (first.matches("^\"")) {
			return getLiteral(first);
		} else if (first.matches("^[0-9]+$")) {
			return getNumber(first);
		} else if (operator.containsKey(first)) {
			return getOperator(first);
		} else {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}
	}

	private LexicalUnit getName(String first) throws IOException {
		this.input = first;
		while (true) {
			int letter = word.read();
			String character = (char) letter + "";
			if ((input + character).matches("^[a-zA-Z0-9_]*")) {
				input = input + character;
			} else {
				word.unread(letter);
				if (Reservation.containsKey(input)) {
					return Reservation.get(input);
				} else {
					LexicalUnit a = new LexicalUnit(LexicalType.NAME, new ValueImpl(input, ValueType.STRING));
					return a;
				}
			}
		}
	}

	public LexicalUnit getLiteral(String first) throws Exception {
		String input = "";
		while (true) {
			nextword = word.read();
			String n = String.valueOf((char) nextword);
			if (nextword == -1) {
				System.err.println("字句解析エラー: ... \"" + n + "\"");
				System.exit(1);
			}
			input = input + n;
			if (n.matches("\"")) {
				input = input.substring(0, input.length() - 1);
				ValueImpl v = new ValueImpl(input, ValueType.STRING);
				LexicalUnit b = new LexicalUnit(LexicalType.LITERAL, v);
				return b;
			}
		}
	}

	private LexicalUnit getOperator(String first) throws IOException {
		this.input = first;
		while (true) {
			nextword = word.read();
			String n = String.valueOf((char) nextword);
			if (operator.containsKey(first + n)) {
				first = first + n;
			} else {
				word.unread(nextword);
				LexicalUnit I = operator.get(first);
				return I;
			}
		}
	}

	private LexicalUnit getNumber(String first) throws IOException {
		this.input = first;
		while (true) {
			nextword = word.read();
			String m = String.valueOf((char) nextword);
			if (m.matches("[0-9]")) {
				input = input + m;
			} else if (m.matches("\\.")) {
				input = input + m;
			} else {
				word.unread(nextword);
				break;
			}
		}
		if (input.matches("^[0-9]+$")) {
			ValueImpl v = new ValueImpl(input, ValueType.INTEGER);
			LexicalUnit c = new LexicalUnit(LexicalType.INTVAL, v);
			return c;

		} else if (input.matches("^[0-9]+\\.[0-9]+$")) {

			ValueImpl i = new ValueImpl(input, ValueType.DOUBLE);
			LexicalUnit d = new LexicalUnit(LexicalType.DOUBLEVAL, i);
			return d;
		}
		return null;
	}

	@Override
	public boolean expect(LexicalType type) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void unget(LexicalUnit token) throws Exception { //LexicalUnitのみ取ればいいので、文字は読まなかった事にする
		list.add(token);//読み込んだものをリストとして保存しておく。
	}
}
