package geo.marcos.chp7.beans;

import java.io.Serializable;

import geo.marcos.chp7.entities.Customer;
import geo.marcos.chp7.entities.CustomerOrder;

public class PurchaseOrder implements Serializable {
  private static final long serialVersionUID = 1L;

  private Customer customer;
  private CustomerOrder customerOrder;
  
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
  
  public Customer getCustomer() {
    return customer;
  }
  
  public void setCustomerOrder(CustomerOrder customerOrder) {
    this.customerOrder = customerOrder;
  }
  
  public CustomerOrder getCustomerOrder() {
    return customerOrder;
  }
}
