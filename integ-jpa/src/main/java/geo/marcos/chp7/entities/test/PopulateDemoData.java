package geo.marcos.chp7.entities.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import geo.marcos.chp7.entities.Address;
import geo.marcos.chp7.entities.BusinessContact;
import geo.marcos.chp7.entities.CartItem;
import geo.marcos.chp7.entities.Customer;
import geo.marcos.chp7.entities.CustomerOrder;
import geo.marcos.chp7.entities.Distributor;
import geo.marcos.chp7.entities.Individual;
import geo.marcos.chp7.entities.InventoryItem;
import geo.marcos.chp7.entities.OrderItem;
import geo.marcos.chp7.entities.Supplier;
import geo.marcos.chp7.entities.Wine;
import geo.marcos.chp7.entities.WineItem;

public class PopulateDemoData {
  public static final String FROM_EMAIL_ADDRESS;
  public static final String TO_EMAIL_ADDRESS;
  
  static {
    Properties properties = new Properties();
    InputStream is = null;
    try {
      is = PopulateDemoData.class.getClassLoader().getResourceAsStream("user..properties");
      properties.load(is);
      FROM_EMAIL_ADDRESS = properties.getProperty("from_email_address");
      TO_EMAIL_ADDRESS = properties.getProperty("from_email_address");
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException ex) {
          Logger.getLogger(PopulateDemoData.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }
  
  private ServiceFacade facade;
  
  public static void main(String[] args) {
    PopulateDemoData.resetData("Chpater07-WineAppUnit-ResourceLocal", System.out);
  }
  
  public static void resetData(String persistenceUnit, PrintStream out) {
    PopulateDemoData pdd = null;
    try {
      pdd = new PopulateDemoData(persistenceUnit);
      
      out.println("Reporting existing data...");
      pdd.showDataCount(out);
      
      out.println("Removing data...");
      pdd.removeAllDemoData(out);
      
      out.println("Reporting data after removal...");
      pdd.showDataCount(out);
      
      out.println("Populating data...");
      pdd.populateDemoCustomer();
      pdd.populateWines();
      
      out.println("Reporting final data...");
      pdd.showDataCount(out);
    } finally {
      if (pdd != null) {
        pdd.releaseEntityManager();
      }
    }
  }
    
  public PopulateDemoData(String persistenceUnit) {
    facade = new ServiceFacade(persistenceUnit);
  }
  
  private void removeAllDemoData(PrintStream out) {
    removeAll(OrderItem.class, out);
    removeAll(Customer.class, out);
    removeAll(Individual.class, out);
    removeAll(Distributor.class, out);
    removeAll(Supplier.class, out);
    removeAll(InventoryItem.class, out);
    removeAll(CartItem.class, out);
    removeAll(Wine.class, out);
  }
  
  private <T> void removeAll(Class<T> entityClass, PrintStream out) {
    int i = 0;
    for (T entity : facade.findAll(entityClass)) {
      facade.removeEntity(entity);
      i++;
    }
    out.println("Removed " + i + " " + entityClass.getSimpleName() + " instances");
  }
  
  private Customer populateDemoCustomer() {
    Address a = new Address("Redwood Shores", "CA", "200 Oracle Pkwy", null, "94065");
    Individual i = new Individual("James", "Brown", "800.888.8000", TO_EMAIL_ADDRESS, a, a, "04/14", "123");
    facade.persistEntity(i);
    
    return i;
  }
  
  private InventoryItem populateWines() {
    InventoryItem ii = null;
    for (int i = 1; i < 7; i++) {
      Wine w = new Wine("USA", "Fine Wine - ranked #" + i, "yerba Buena " + i, 90, "Napa Valley", new Float(10 + 1), "Zinfandel", 2000 + i);
      facade.persistEntity(w);
      ii = new InventoryItem(10 + 1, w, new java.util.Date(System.currentTimeMillis()), new Float(1 + 1));
      facade.persistEntity(ii);
    }
    for (int i = 4; i < 10; i++) {
      Wine w = new Wine("France", "FineWine - ranked #" + i, "Chateau Brown " + i, 90, "Loire Valley", new Float(12 + i), "Zinfandel", 2000+i);
      facade.persistEntity(w);
      ii = new InventoryItem(10 + i, w, new java.util.Date(System.currentTimeMillis()), new Float(1 + i));
    }
    
    return ii;
  }
  
  public void showDataCount(PrintStream out) {
    out.println(facade.getCount(Address.class) + " Addresses found");
    out.println(facade.getCount(BusinessContact.class) + " Business Contacts found");
    out.println(facade.getCount(CustomerOrder.class) + " Customer Orders found");
    out.println(facade.getCount(Wine.class) + " Wines found");
    out.println(facade.getCount(WineItem.class) + " Wine Items found");
  }
  
  private void releaseEntityManager() {
    if (facade != null) {
      facade.close();
    }
  }
}
