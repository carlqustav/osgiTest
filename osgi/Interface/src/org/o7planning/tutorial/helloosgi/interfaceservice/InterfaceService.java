package org.o7planning.tutorial.helloosgi.interfaceservice;

import org.osgi.service.event.EventAdmin;

public interface InterfaceService {
	
	public void sendRequest(String numString1, String numString2, String operation, String lang);
}
