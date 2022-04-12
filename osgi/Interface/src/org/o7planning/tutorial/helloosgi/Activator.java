package org.o7planning.tutorial.helloosgi;

import java.security.Provider.Service;
import java.util.concurrent.TimeUnit;

import org.o7planning.tutorial.InterfaceSwing;
import org.o7planning.tutorial.helloosgi.calculatorservice.CalculateService;
import org.o7planning.tutorial.helloosgi.interfaceservice.InterfaceService;
import org.o7planning.tutorial.helloosgi.interfaceservice.impl.InterfaceServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.EventAdmin;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private static InterfaceSwing swing;
	private static EventAdmin eventAdmin;
	InterfaceService service;
		
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
		
		ServiceReference ref = context.getServiceReference(EventAdmin.class.getName());
		eventAdmin = (EventAdmin) context.getService(ref);

		System.out.println("Registry Service InterfaceService...");
		this.registryInterfaceService();
		swing.runInterface(service);
		
		System.out.println("OSGi InterfaceService Started");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("OSGi InterfaceService Stopped");
	}

	private void registryInterfaceService() {
		if(eventAdmin != null) {	
			service = new InterfaceServiceImpl(eventAdmin);	
			System.out.println(eventAdmin.toString());
		}
		
		context.registerService(InterfaceService.class, service, null);
		System.out.println("InterfaceService has been registered with");
	}

}
