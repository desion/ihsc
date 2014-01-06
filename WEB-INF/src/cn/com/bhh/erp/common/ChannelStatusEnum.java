package cn.com.bhh.erp.common;

public enum ChannelStatusEnum {
	DISABLE("禁用", 0), NORMAL("正常", 1);
	// 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private ChannelStatusEnum(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.name;  
    } 
    
    public static String nameOf(int value){
    	ChannelStatusEnum op = ChannelStatusEnum.values()[value];
    	return op.toString();
    }
}
