package com.zversal.employeeportal.service;

public class RemoteService 
{
	public String remoteService()
	{
		try
		{
			Thread.sleep(100);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		return "Remote Service Successful";
	}

}
