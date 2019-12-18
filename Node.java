package newlang4;

public class Node { //Nodeクラス
    NodeType type; //NodeType型の変数Type
    Environment env; //Enviroment型の変数env

    /** Creates a new instance of Node */
    public Node() { //コンストラクタ
    }
    public Node(NodeType my_type) { //コンストラクタのオーバーロード
        type = my_type;
    }
    public Node(Environment my_env) { //コンストラクタのオーバーロード
        env = my_env;
    }
    
    public NodeType getType() { //NodeType型のgetTypeメソッド
        return type;
    }
    
    public boolean parse() throws Exception { //boolean(ブーリアン)型のparseメソッド、スロー宣言投げる
        return true;
    }
    
    public Value getValue() throws Exception {//Value型のgetValueメソッド,スロー宣言投げる
        return null;
    }
 
    public String toString() { //String型のtoStringメソッド
    	if (type == NodeType.END) return "END";
    	else return "Node";        
    }

}
