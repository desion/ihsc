package cn.com.bhh.erp.common;

public enum OrderStatusEnum {
	CREATE("待充值", 0), TOPUPING("充值中", 1), SUCCESS("成功", 2),FAILED("失败",3);
	// 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private OrderStatusEnum(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.name;  
    } 
    
    public static String nameOf(int value){
    	OrderStatusEnum op = OrderStatusEnum.values()[value];
    	return op.toString();
    }
}
