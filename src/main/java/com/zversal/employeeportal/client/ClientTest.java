package com.zversal.employeeportal.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import com.zversal.employeeportal.service.RemoteService;

public class ClientTest
{
	public static RemoteService remoteService=new RemoteService();
	public static Task task=new Task(remoteService);
	public static void service()
	{
		ExecutorService executorService=null;
		try {
			executorService=Executors.newSingleThreadExecutor();
			long sTime=System.currentTimeMillis();
			Future<String> future=executorService.submit(new Task(new RemoteService()));
			
			while(!future.isDone())
			{
				long totalTime=System.currentTimeMillis()-sTime;
				if(totalTime>20000)
				{
					System.out.println("Task is taking long time to execute so cancelling");
					future.cancel(true);
				}
			}
			try {
				String result=future.get(20,TimeUnit.SECONDS);
				System.out.println(result);
			}
			catch(InterruptedException | ExecutionException | TimeoutException e)
			{
				e.printStackTrace();
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally {
				if(executorService!=null)
				{
					executorService.shutdown();
				}
		}
	}
}
