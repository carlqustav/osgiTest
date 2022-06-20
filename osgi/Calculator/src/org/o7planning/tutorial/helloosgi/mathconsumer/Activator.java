package org.o7planning.tutorial.helloosgi.mathconsumer;

import org.o7planning.tutorial.helloosgi.calculatorservice.CalculateService;
import org.o7planning.tutorial.helloosgi.calculatorservice.impl.CalculateServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	static BundleContext context;
	CalculateService service;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		this.registryCalculateService();

		System.out.println("Calculator Started");
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
