package com.javaee.jms.mdbs;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "BolidMonitor", activationConfig = {

		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/myQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")

})
public class BolidMonitor implements MessageListener {

	private static Logger LOGGER = Logger.getLogger(BolidMonitor.class.toString());

	public void onMessage(Message messange) {

		if (messange instanceof TextMessage) {
			try {
				String text = ((TextMessage) messange).getText();
				LOGGER.info("Message send to BolidMonitor from Bolid : " + text);

			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
