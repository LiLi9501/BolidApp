package com.javaee.servlets;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaee.jms.MessangeProducer;

@JMSDestinationDefinitions(value = {
		@JMSDestinationDefinition(name = "java:/queue/myQueue", interfaceName = "javax.jms.Queue", destinationName = "BolidMonitor"),
		@JMSDestinationDefinition(name = "java:/topic/myTopic", interfaceName = "javax.jms.Topic", destinationName = "BolidLogger") })

@WebServlet(urlPatterns = "/")
public class Bolid extends HttpServlet {

	private static final long serialVersionUID = 3234027581994367438L;

	public static double getRandomIntegerBetweenRange(double min, double max) {
		double x = (int) (Math.random() * ((max - min) + 1)) + min;
		return x;
	}

	@EJB
	MessangeProducer producer;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int speed = (int) getRandomIntegerBetweenRange(0, 350);
		int oil = (int) getRandomIntegerBetweenRange(300, 400);
		double tire = getRandomIntegerBetweenRange(1.7, 2.2);

		String data = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()).toString();
		String messange = data + "\nVehicle speed: " + speed + " km/h" + "\nOil pressure is: " + oil + " kPa"
				+ "" + "\nTire pressure is : " + tire + " bar" + "/n";

		producer.sendMessange2(messange);
		producer.sendMessange(messange);
		resp.getWriter().write("Message from bolid: " + messange);

	}

	private Map<Long, Bolid> categoryMap;

	public void updateCategoryMap(Integer startPosition, Integer maxResult) {
		categoryMap = new HashMap<Long, Bolid>();

		int speed = (int) getRandomIntegerBetweenRange(0, 350);
		int oil = (int) getRandomIntegerBetweenRange(300, 400);
		double tire = getRandomIntegerBetweenRange(1.7, 2.2);

		String data = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()).toString();
		String messange = data + "\nVehicle speed: " + speed + " km/h" + "\nOil pressure is: " + oil + " kPa"
				+ "" + "\nTire pressure is : " + tire + " bar" + "/n";

		producer.sendMessange2(messange);
		
		producer.sendMessange(messange);

	}
}
