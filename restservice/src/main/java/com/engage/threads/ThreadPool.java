package com.engage.threads;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	
	public static ExecutorService executor = Executors.newFixedThreadPool(7);   

}