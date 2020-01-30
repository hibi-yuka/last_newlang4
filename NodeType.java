package newlang4;

public enum NodeType {
	PROGRAM, //<program>
	STMT_LIST, //<stmt_list>
	STMT, //<stmt>
	FOR_STMT, //
	ASSIGN_STMT, //
	BLOCK,  //<block>
	IF_BLOCK, //<if_prefix>
	LOOP_BLOCK, //
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
//<varlist>
//<if_prefix>
//<else_block>
//<else_if_block>
//<subst>
//<call_sub>
//<var>
//<leftvar>
//<expr_list>

