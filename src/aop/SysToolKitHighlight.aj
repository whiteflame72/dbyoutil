package aop;

import com.wtc.wmsp.fw.SysToolKit;

public aspect SysToolKitHighlight {
	public pointcut showAllSourceWithSysToolKit(): 
		execution(static * SysToolKit.getConnection(*)) && !within(SysToolKitHighlight);
	
	
	//declare error : showAllSourceWithSysToolKit() : "This source uses GTC SysToolKit codes.";
	Object around():showAllSourceWithSysToolKit(){
		return proceed();
	}
}
