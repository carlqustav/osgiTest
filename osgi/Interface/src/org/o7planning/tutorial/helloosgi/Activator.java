package org.o7planning.tutorial.helloosgi;

import org.o7planning.tutorial.InterfaceSwing;
import org.o7planning.tutorial.helloosgi.calculatorservice.CalculateService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private static InterfaceSwing swing;
		
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		ServiceReference<?> serviceReference = context
				.getServiceReference(CalculateService.class);
		CalculateService service = (CalculateService) context
				.getService(serviceReference);

		System.out.println(service.toString());

		System.out.println("Registry Service InterfaceService...");
		swing.runInterface(service);
		
		System.out.println("OSGi InterfaceService Started");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("OSGi InterfaceService Stopped");
	}
}
