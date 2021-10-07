package com.zversal.employeeportal.client;

import java.util.concurrent.Callable;

import com.zversal.employeeportal.service.RemoteService;

public class Task implements Callable<String>
{
	private RemoteService remoteService;
	public Task(RemoteService remoteService)
	{
		this.remoteService=remoteService;
	}
	public String call() throws Exception
	{
		return remoteService.remoteService();
	}
}
