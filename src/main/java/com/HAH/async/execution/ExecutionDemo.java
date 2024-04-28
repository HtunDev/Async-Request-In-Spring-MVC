package com.HAH.async.execution;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import javax.sound.midi.Soundbank;

public class ExecutionDemo {

	public static void main(String[] args) {

		var executor = Executors.newFixedThreadPool(5);

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("Start Runnable");
					Thread.sleep(1000);
					System.out.println("End Runnable");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		var runnableFuture = executor.submit(runnable);

		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("Start Callable");
				Thread.sleep(1000);
				System.out.println("End Callable");
				return "Callable result is Fine!";
			}
		};
		
		var callableFuture = executor.submit(callable); 
		while(true) {
			if((runnableFuture.isCancelled() || runnableFuture.isDone()) &&
					(callableFuture.isCancelled() || callableFuture.isDone()));
			
			try {
				System.out.println("Callable Result is %s".formatted(callableFuture.get()));
				executor.shutdown();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
		try {
			Thread.sleep(500);
			System.out.println("Check Result");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
