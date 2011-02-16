package uk.me.doitto.mypackage.service;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Init {
	
//	private InetService inetService;
//	public void setInetService(InetService inetService) {
//		this.inetService = inetService;
//	}
	
	ExecutorService unboundedPool = Executors.newCachedThreadPool();

	public Init () {
		System.out.println("***************************** Init Started **********************************");		
		for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
			System.out.println(entry.getKey().toString() + ": " + entry.getValue().toString());
		}
		
//		WebApplicationContextUtils.getWebApplicationContext(sc)
		// one method . . .
//		(new Thread(new MyThread1())).start();
		// . . . and another
//		(new MyThread2()).start();
		// using a cached thread pool, runnable . . .
//		unboundedPool.execute(new RunnableExecutorBasedThread());
		// using a cached thread pool, callable . . .
//		Future<String> future = unboundedPool.submit(new CallableExecutorBasedThread<String>());
		System.out.println("***************************** Init Complete *********************************");
	}
}
