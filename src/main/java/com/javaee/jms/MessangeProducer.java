package com.javaee.jms;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

@Stateless
@LocalBean
public class MessangeProducer {


	
	@Resource(mappedName="java:/queue/myQueue") 
	Queue myQueue;
	
	@Resource(mappedName="java:/topic/myTopic")
	Topic myTopic;
	
	@Inject	
	JMSContext jmsContext;
	
	public void sendMessange (String message) {
		jmsContext.createProducer().send(myQueue, message);
	}
	
	public void sendMessange2 (String message) {
		jmsContext.createProducer().send(myTopic, message);
	}
	
}
