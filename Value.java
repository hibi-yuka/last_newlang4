package newlang4;

public abstract class Value { //抽象クラスであるvaluue
// �������ׂ��R���X�g���N�^
    public Value(String s) {}; //空のコンストラクタ//なぜvalueクラスを作った？。計算した時に、
    public Value(int i) {};
    public Value(double d) {};
    public Value(boolean b) {};
    public Value(String s, ValueType t) {};
    public abstract String get_sValue();//抽象メソッド、メソッドの中身は記述しないので{}i

	public abstract String getSValue();
	// �X�g�����O�^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public abstract int getIValue();
    	// �����^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public abstract double getDValue();
    	// �����_�^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public abstract boolean getBValue();
    	// �_���^�Œl�����o���B�K�v������΁A�^�ϊ����s���B
    public abstract ValueType getType();
}
