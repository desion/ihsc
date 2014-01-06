package cn.com.bhh.erp.common;

public enum StatusEnum {
	ADD("新增", 0), LISTING("上架", 1), CHANGE("调价", 2),DELIST("下架",3);
	// 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private StatusEnum(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.name;  
    } 
    
    public static String nameOf(int value){
    	StatusEnum op = StatusEnum.values()[value];
    	return op.toString();
    }
}
