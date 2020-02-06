package newlang4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PushbackReader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

	        LexicalAnalyzer lex;
	        LexicalUnit		first;
	        Environment		env;

	        System.out.println("basic parser");

	        File file = new File("test05.bas");
			FileReader fr = null;
			try {
				fr = new FileReader(file);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			PushbackReader pr = new PushbackReader(fr);
			lex = new LexicalAnalyzerImpl(pr);
	        env = new Environment(lex);
	        first = lex.get();

	        if (Program.isFirst(first)) {

	        	Node handler = Program.getHandler(first, env);
	        	handler.parse();
	        	System.out.println(handler);
	        }
	        else System.out.println("syntax error");
	}
}
