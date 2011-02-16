package uk.me.doitto.mypackage.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class ProfilingInterceptor implements MethodInterceptor {

	public Object invoke (MethodInvocation invocation) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(invocation.getMethod().getName());		
		Object returnValue = invocation.proceed();		
		stopWatch.stop();
		dumpInfo(invocation, stopWatch.getTotalTimeMillis());
		return returnValue;
	}

	private void dumpInfo (MethodInvocation invocation, long ms) {
		System.out.println("Execute method: "+ invocation.getMethod().getName());
		System.out.println("On object of type: "+ invocation.getThis().getClass().getName());
		System.out.println("With arguments:");
		for (Object arg : invocation.getArguments()) {
			System.out.println("    > " + arg.toString());
		}
		System.out.println("Took: " + ms + " ms");
	}
}
