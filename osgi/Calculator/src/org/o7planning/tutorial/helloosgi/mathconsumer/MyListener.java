package org.o7planning.tutorial.helloosgi.mathconsumer;

import org.o7planning.tutorial.helloosgi.interfaceservice.InterfaceService;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

public class MyListener implements ServiceListener {

	private InterfaceService is = null;
	private CalculatorThread t = null;

	public void serviceChanged(ServiceEvent e) {

		switch (e.getType()) {
		case ServiceEvent.REGISTERED:
			System.out.println("Service REGISTERED");
			is = (InterfaceService) Activator.context.getService(e.getServiceReference());
			startUserThread();
			break;
		case ServiceEvent.MODIFIED:
			System.out.println("Service MODIFIED");
			stopUserThread();
			is = (InterfaceService) Activator.context.getService(e.getServiceReference());
			startUserThread();
			break;
		case ServiceEvent.UNREGISTERING:
			System.out.println("Service UNREGISTERED");
			stopUserThread();
			break;

		default:
			break;
		}

	}

	public void startUserThread() {
		t = new CalculatorThread(is);
		t.start();
	}

	public void stopUserThread() {
		if (t == null) {
			return;
		}
		t.stopThread();
		try {
			t.join();
		} catch (InterruptedException e) {
		}
		is = null;
	}

}
