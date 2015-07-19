package aop;

import com.wtc.wmsp.fw.WebToolKit;

public aspect WebToolKitHighlight {
	public pointcut showAllSourceWithWebToolKit(): 
		call(static * WebToolKit.getConnection(*)) && !within(WebToolKitHighlight);
	
	
	//declare error : showAllSourceWithSysToolKit() : "This source uses GTC SysToolKit codes.";
	Object around():showAllSourceWithWebToolKit(){
		return proceed();
	}
}
