package org.o7planning.tutorial.helloosgi.mathconsumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

@Component(
		service = EventHandler.class,
		immediate = true,
		property = EventConstants.EVENT_TOPIC
        + "="  + "org/o7planning/tutorial/helloosgi/interfaceservice/impl/CALC")
public class CalculatorHandler implements EventHandler {

	@Override
	public void handleEvent(Event event) {
		System.out.println("Calculator <-" + event.getProperty("target"));
	}

}

