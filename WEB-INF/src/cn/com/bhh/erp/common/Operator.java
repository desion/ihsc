package cn.com.bhh.erp.common;

public enum Operator {
	MOBILE("移动", 1), UNICOM("联通", 2), TELECOM("电信", 3),OTHER("其它",4);
	// 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private Operator(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.name;  
    } 
    
    public static String nameOf(int value){
    	Operator op = Operator.values()[value];
    	return op.toString();
    }
}
