package org.o7planning.tutorial.helloosgi.interfaceservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.o7planning.tutorial.helloosgi.interfaceservice.InterfaceService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;


@Component
public class InterfaceServiceImpl implements InterfaceService {

	@Reference
	final EventAdmin eventAdmin;
	public static final String TOPIC_HEADER = "org/o7planning/tutorial/helloosgi/interfaceservice/impl/";
	
	public InterfaceServiceImpl() {
		eventAdmin = null;
	}
	
	public InterfaceServiceImpl(EventAdmin ea) {
		eventAdmin = ea;
	}
	
	BundleContext context;
	
	@Override
	public void sendRequest(String numString1, String numString2, String operation, String lang) {
		Map<String, String[]> properties = new HashMap<>();
		properties.put("target", new String[]{numString1,numString2,operation,lang});
		
		Event event = new Event(TOPIC_HEADER+"CALC",properties);
		eventAdmin.postEvent(event);
		
		System.out.println("Interface -> " + event.toString() + "eventadmin " + eventAdmin);
	}

}
