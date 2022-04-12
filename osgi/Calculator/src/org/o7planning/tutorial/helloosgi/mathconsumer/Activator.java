package org.o7planning.tutorial.helloosgi.mathconsumer;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.o7planning.tutorial.helloosgi.calculatorservice.CalculateService;
import org.o7planning.tutorial.helloosgi.calculatorservice.impl.CalculateServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.EventConstants;

public class Activator implements BundleActivator {

	static BundleContext context;
	private CalculatorHandler ch;
	CalculateService service;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		ch = new CalculatorHandler();
		
		//getting InterfaceService -redacted-
		/*
		ServiceReference<?> serviceReference = context
				.getServiceReference(InterfaceService.class);
		InterfaceService service = (InterfaceService) context
				.getService(serviceReference);

		System.out.println(service.toString());
		*/
		
		this.registryCalculateService();

		//getting event handler -redacted-
		/*
		Dictionary<String, String> props = new Hashtable<String,String>();
		props.put(EventConstants.EVENT_TOPIC, "org/o7planning/tutorial/helloosgi/interfaceservice/impl/");

		context.registerService(CalculatorHandler.class.getName(), new CalculatorHandler(), props);
		*/
		System.out.println("Calculator Started with + " + context.getAllServiceReferences(CalculatorHandler.class.getName(), null));
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Calculator Stopped");
	}
	
	private void registryCalculateService() {
		service = new CalculateServiceImpl();	
		context.registerService(CalculateService.class, service, null);
		System.out.println("CalculateService has been registered");
	}

}
