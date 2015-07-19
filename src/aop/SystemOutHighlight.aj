package aop;

public aspect SystemOutHighlight {
	public pointcut outcall():call(* java.io.PrintStream.*(String)) 
	&& !within(SystemOutHighlight);

	Object around():outcall(){
		return proceed();
	}
}
