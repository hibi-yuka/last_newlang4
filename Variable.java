package newlang4;

import java.util.EnumSet;
import java.util.Set;

public class Variable extends Node {
	    String var_name;
	    Value v;
	    static Set<LexicalType> first = EnumSet.of(LexicalType.NAME);

	    /** Creates a new instance of variable */
	    public Variable(String name) {
	        var_name = name;
	    }
	    public Variable(LexicalUnit u) {
	        var_name = u.getValue().getSValue();
	    }

	    public static boolean isFirst(LexicalUnit funit) {
	        return first.contains(funit.getType());
	    }

	    public static Node getHandler(LexicalUnit funit, Environment my_env) {
	        if (isFirst(funit)) {
	        	Variable v;

	        	try {
		        	LexicalUnit lu = my_env.getInput().get();
		        	my_env.getInput().unget(lu);
		            String s = lu.getValue().getSValue();
		            v = my_env.getVariable(s);
		            return v;
	        	}
	        	catch(Exception e) {}
	        }
	        return null;
	    }

	    public void setValue(Value my_v) {
	        v = my_v;
	    }

	    public Value getValue() {
	        return v;
	    }

	    public String toString() {
	    	String str = var_name;

	    	return str;
	    }

}

