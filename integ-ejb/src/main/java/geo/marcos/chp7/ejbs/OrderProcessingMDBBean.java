package geo.marcos.chp7.ejbs;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import geo.marcos.chp7.beans.PurchaseOrder;
import geo.marcos.chp7.entities.Customer;
import geo.marcos.chp7.entities.CustomerOrder;
import geo.marcos.chp7.entities.InventoryItem;
import geo.marcos.chp7.entities.OrderItem;
import geo.marcos.chp7.entities.Wine;
import geo.marcos.chp7.entities.test.PopulateDemoData;

@MessageDriven(mappedName="PurchaseOrderTopic", activationConfig= {
	@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic")
})
public class OrderProcessingMDBBean implements MessageListener {
	private PurchaseOrder po;
	@PersistenceContext(unitName = "Chapter07-WineAppUnit-JTA")
	private EntityManager em;
	@Resource(mappedName="StatusMessageTopicConnectionFactory")
	private TopicConnectionFactory statusMessageTopicCF;
	@Resource(mappedName="StatusMessageTopic")
	private Topic statusTopic;
	
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objMessage = (ObjectMessage) message;
				Object obj = objMessage.getObject();
				if (obj instanceof PurchaseOrder) {
					po = (PurchaseOrder) obj;
					processOrder(po);
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	private void processOrder(PurchaseOrder po) {
		Customer customer = po.getCustomer();
		CustomerOrder order = po.getCustomerOrder();
		
		for (OrderItem oItem : order.getOrderItemList()) {
			Wine wine = oItem.getWine();
			int quant = oItem.getQuantity();
			deductInventory(wine, quant);
		}
		
		String from = PopulateDemoData.FROM_EMAIL_ADDRESS;
		String to = customer.getEmail();
		String content = 
				"Your order has been processed. "
				+ "If you have question call Beginning EJB Wine Store "
				+ "Application with ord id # "
				+ po.getCustomerOrder().getId().toString();
		sendStatus(from, to, content);
	}
	
	private void deductInventory(Wine tempWine, int deductQuant) {
		InventoryItem item = 
				em.createNamedQuery("InventoryItem.findItemByWine", InventoryItem.class)
				.setParameter("wine", tempWine).getSingleResult();
		int newQuant = item.getQuantity() - deductQuant;
		item.setQuantity(newQuant);
	}
	
	private void sendStatus(String from, String to, String content) {
		try {
			System.out.println("Before status TopicCF connection");
			Connection connection = statusMessageTopicCF.createConnection();
			System.out.println("Created connection");
			connection.start();
			System.out.println("Started connection");
			System.out.println("Stating Topic Session");
			Session topicSession =
					connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = topicSession.createProducer(statusTopic);
			System.out.println("created producer");
			MapMessage message = topicSession.createMapMessage()	;
			message.setStringProperty("from", from);
			message.setStringProperty("to", to);
			message.setStringProperty("subject", "Status of your wine order");
			message.setStringProperty("content", content);
			System.out.println("before send");
			publisher.send(message);
			System.out.println("after send");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
