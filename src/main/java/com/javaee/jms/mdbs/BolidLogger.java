package com.javaee.jms.mdbs;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.javaee.util.FileUtil;

@MessageDriven(name = "BolidLogger", activationConfig = {

		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/myTopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")

})

public class BolidLogger implements MessageListener {

	private static Logger LOGGER = Logger.getLogger(BolidLogger.class.toString());
	private final String messageFile = "messages.txt";

	public void onMessage(Message messange) {

		if (messange instanceof TextMessage) {
			try {
				String text = ((TextMessage) messange).getText();
				LOGGER.info("Message send to BolidLogger from Bolid : " + text);

				FileUtil util = new FileUtil(messageFile);
				util.saveMessageToFile(text);

			} catch (JMSException e) {
				LOGGER.log(Level.SEVERE, "Error on processing data");
				e.printStackTrace();
			}
		}
	}
}