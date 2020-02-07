package newlang4;

public enum NodeType {
	PROGRAM, //<program>
	STMT_LIST, //<stmt_list>
	STMT, //<stmt>
	FOR_STMT, //<for>
	ASSIGN_STMT, //<subst>
	BLOCK,  //<block>
	IF_BLOCK, //<if>
	LOOP_BLOCK, //Loop
	COND, //<cond>
    EXPR_LIST, //<expr_list>
	EXPR, //<expr>
	FUNCTION_CALL, //<call_func>
    STRING_CONSTANT,
    INT_CONSTANT,
    DOUBLE_CONSTANT,
    BOOL_CONSTANT,
    END,
}


