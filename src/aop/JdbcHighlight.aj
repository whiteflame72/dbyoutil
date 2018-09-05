package aop;

public aspect JdbcHighlight {
	pointcut showAllSourceWithJDBC(): call(* java.sql.Connection.*(*)) && !within(JdbcHighlight);
		
	//declare error : showAllSourceWithJDBC() : "This source uses JDBC directly.";
	Object around():showAllSourceWithJDBC(){
		return proceed();
	}
}