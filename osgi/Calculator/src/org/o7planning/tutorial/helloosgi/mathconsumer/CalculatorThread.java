package org.o7planning.tutorial.helloosgi.mathconsumer;

import org.o7planning.tutorial.helloosgi.interfaceservice.InterfaceService;

public class CalculatorThread extends Thread {

	private InterfaceService is = null;

	public CalculatorThread(InterfaceService s) {
	is = s;
	}

	private boolean running = true;

	@Override
	public void run() {

	while (running) {
		
	//String[] calcString = is.sendRequest("on bir", "on iki", "add", "tr");
	//System.out.println(calcString[0] + " " + calcString[1]);

	try {
	sleep(3000);
	} catch (InterruptedException e) {
	}

	}

	}

	public void stopThread() {
	running = false;
	}

	
}
