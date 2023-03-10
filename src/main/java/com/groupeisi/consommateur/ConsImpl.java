package com.groupeisi.consommateur;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsImpl implements Icons{
	 private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	 private ConnectionFactory connectionFactory;
	 private Connection connection;
	 private Session session;
	 private  Destination destination;
	 private MessageConsumer consumer;
	 private Message message;
	public ConsImpl() throws JMSException {
		this.connectionFactory = new ActiveMQConnectionFactory(url);
		connection = connectionFactory.createConnection();
		
	}
	@Override
	public String getMessage(String subject) throws JMSException {
		 connection.start();
		 session = connection.createSession(false,
	                Session.AUTO_ACKNOWLEDGE);
		 destination = session.createQueue(subject);
		 consumer = session.createConsumer(destination);
		 message = consumer.receive();
		 String mess="";
		 if (message instanceof TextMessage) {
	             mess = ((TextMessage) message).getText().toString();
	            return mess;
	        }
	        connection.close();
		return mess;
	}

}
