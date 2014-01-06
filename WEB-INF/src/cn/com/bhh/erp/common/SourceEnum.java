package cn.com.bhh.erp.common;

public enum SourceEnum {
	ZHICHONG("直充", 0), TMALL("天猫", 1), FIX("补充", 2),BATCHFIX("批充",3);
	// 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private SourceEnum(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.name;  
    } 
    
    public static String nameOf(int value){
    	SourceEnum op = SourceEnum.values()[value];
    	return op.toString();
    }
}
