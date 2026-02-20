package geo.marcos.chp7.entities.test;

import java.util.List;

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

public class ServiceFacadeClient {

  public static void main(String[] args) {
    try {
      final ServiceFacade serviceFacade = new ServiceFacade();
      insertData(serviceFacade);
      
      for (Wine wine : (List<Wine>) serviceFacade.getWineFindByYear(2006)) {
        printWine(wine);
      }
      for (Wine wine : (List<Wine>) serviceFacade.getWineFindByCountry("Argentina")) {
        printWine(wine);
      }
      for (Wine wine : (List<Wine>) serviceFacade.getWineFindByVarietal("Malbec")) {
        printWine(wine);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  private static void insertData(final ServiceFacade serviceFacade) {
    // createble type
    Address a;
    CartItem ci;
    Customer c;
    CustomerOrder co;
    Distributor d;
    Individual i;
    InventoryItem ii;
    OrderItem oi;
    Supplier s;
    Wine w;
    
    a = new Address("San Mateo", "CA", "1301 Ashwood Ct", null, "94402");
    d = new Distributor("Brian", "Henson", "(650) 555-1212", "a@b", a, a, "Acme Plumbing", new Integer(1), "PREFERRED");
    i = new Individual("Andy", "Mockler", "(650) 555-1122", "a@b", a, a, "0914", "5555444433331");
    w = new Wine("Argentina", "Malbec", "Michel Torino", 91, "Mendoza", new Float(100), "Malbec", 2006);
    co = new CustomerOrder("PROCESSING", i);
    s = new Supplier("Parker", "Quillen", "(415) 999-0817", a);
    s.getWineList().add(w);
    w.getSupplierList().add(s);
    ci = new CartItem(10, w);
    i.addShippingAddress(a);
    i.addShippingAddress(a);
    d.addCartItem(ci);
    serviceFacade.persistEntity(d);
    serviceFacade.persistEntity(i);
    serviceFacade.persistEntity(s);
  }
  
  private static void printCartItem(CartItem cartItem) {
    System.out.println("createdDate: " + cartItem.getCreatedDate());
    System.out.println("customer: " + cartItem.getCustomer());
    System.out.println("id: " + cartItem.getId());
    System.out.println("quantity: " + cartItem.getQuantity());
    System.out.println("version: " + cartItem.getVersion());
    System.out.println("wine: " + cartItem.getWine());
  }
  
  private static void printBusinessContact(BusinessContact businessContact) {
    System.out.println("firstName: " + businessContact.getFirstName());
    System.out.println("id: " + businessContact.getId());
    System.out.println("lastName: " + businessContact.getLastName());
    System.out.println("phone: " + businessContact.getPhone());
    System.out.println("version: " + businessContact.getVersion());
  }
  
  private static void printCustomerOder(CustomerOrder customerOrder) {
    System.out.println("creationDate: " + customerOrder.getCreationDate());
    System.out.println("id: " + customerOrder.getId());
    System.out.println("status: " + customerOrder.getStatus());
    System.out.println("version: " + customerOrder.getVersion());
    System.out.println("customer: " + customerOrder.getCustomer());
    System.out.println("orderListItem: " + customerOrder.getOrderItemList());
  }
  
  private static void printCustomer(Customer customer) {
    System.out.println("email: " + customer.getEmail());
    System.out.println("cartItemList: " + customer.getCartItemList());
    System.out.println("customerOrderList: " + customer.getCustomerOrderList());
    System.out.println("billingAddressList: " + customer.getBillingAddressList());
    System.out.println("shippingAddressList: " + customer.getShippingAddressList());
    System.out.println("defaultShippingAddress: " + customer.getDefaultShippingAddress());
    System.out.println("defaultBillingAddress: " + customer.getDefaultBillingAddress());
    System.out.println("firstName: " + customer.getFirstName());
    System.out.println("id: " + customer.getId());
    System.out.println("lastName: " + customer.getLastName());
    System.out.println("phone: " + customer.getPhone());
    System.out.println("version: " + customer.getVersion());
  }
  
  public static void printAddress(Address address) {
    System.out.println("city: " + address.getCity());
    System.out.println("id: " + address.getId());
    System.out.println("state: " + address.getState());
    System.out.println("street1: " + address.getStreet1());
    System.out.println("street2: " + address.getStreet2());
    System.out.println("version: " + address.getVersion());
    System.out.println("zipCode: " + address.getZipCode());
  }
  
  private static void printInventoryItem(InventoryItem inventoryItem) {
    System.out.println("stockDate: " + inventoryItem.getStockDate());
    System.out.println("wholesalePrice: " + inventoryItem.getWholesalePrice());
    System.out.println("id: " + inventoryItem.getId());
    System.out.println("quantity: " + inventoryItem.getQuantity());
    System.out.println("version: " + inventoryItem.getVersion());
    System.out.println("wine: " + inventoryItem.getWine());
  }
  
  private static void printSupplier(Supplier supplier) {
    System.out.println("paymentAddress: " + supplier.getPaymentAddress());
    System.out.println("wineList: " + supplier.getWineList());
    System.out.println("firstName: " + supplier.getFirstName());
    System.out.println("id: " + supplier.getId());
    System.out.println("lastName: " + supplier.getLastName());
    System.out.println("phone: " + supplier.getPhone());
    System.out.println("version: " + supplier.getVersion());
  }
  
  private static void printWineItem(WineItem wineItem) {
    System.out.println("id: " + wineItem.getId());
    System.out.println("quantity: " + wineItem.getQuantity());
    System.out.println("version: " + wineItem.getVersion());
    System.out.println("wine: " + wineItem.getWine());
  }
  
  private static void printIndividual(Individual individual) {
    System.out.println("ccExpDate: " + individual.getCcExpDate());
    System.out.println("ccNum: " + individual.getCcNum());
    System.out.println("email: " + individual.getEmail());
    System.out.println("cartItemList: " + individual.getCartItemList());
    System.out.println("customerOrderList: " + individual.getCustomerOrderList());
    System.out.println("billingAddressList: " + individual.getBillingAddressList());
    System.out.println("shippingAddressList: " + individual.getShippingAddressList());
    System.out.println("defaultShippingAddress: " + individual.getDefaultShippingAddress());
    System.out.println("defaultBillingAddress: " + individual.getDefaultBillingAddress());
    System.out.println("firstName: " + individual.getFirstName());
    System.out.println("id: " + individual.getId());
    System.out.println("lastName: " + individual.getLastName());
    System.out.println("phone: " + individual.getPhone());
    System.out.println("version: " + individual.getVersion());
  }
  
  private static void printOrderItem(OrderItem orderItem) {
    System.out.println("orderDate: " + orderItem.getOrderDate());
    System.out.println("price: " + orderItem.getPrice());
    System.out.println("shipDate: " + orderItem.getShipDate());
    System.out.println("status: " + orderItem.getStatus());
    System.out.println("customerOrder: " + orderItem.getCustomerOrder());
    System.out.println("id: " + orderItem.getId());
    System.out.println("quantity: " + orderItem.getQuantity());
    System.out.println("version: " + orderItem.getVersion());
    System.out.println("wine: " + orderItem.getWine());
  }
  
  static void printDistributor(Distributor distributor) {
    System.out.println("companyName: " + distributor.getCompanyName());
    System.out.println("discount: " + distributor.getDiscount());
    System.out.println("memberStatus: " + distributor.getMemberStatus());
    System.out.println("email: " + distributor.getEmail());
    System.out.println("cartItemList: " + distributor.getCartItemList());
    System.out.println("customerOrderList: " + distributor.getCustomerOrderList());
    System.out.println("billingAddressList: " + distributor.getBillingAddressList());
    System.out.println("shippingAddressList: " + distributor.getShippingAddressList());
    System.out.println("defaultShippingAddress: " + distributor.getDefaultShippingAddress());
    System.out.println("defaultBillingAddress: " + distributor.getDefaultBillingAddress());
    System.out.println("firstName: " + distributor.getFirstName());
    System.out.println("id: " + distributor.getId());
    System.out.println("lastName: " + distributor.getLastName());
    System.out.println("phone: " + distributor.getPhone());
    System.out.println("version: " + distributor.getVersion());
    
  }
  
  private static void printWine(Wine wine) {
    System.out.println("country: " + wine.getCountry());
    System.out.println("description: " + wine.getDescription());
    System.out.println("id: " + wine.getId());
    System.out.println("name: " + wine.getName());
    System.out.println("rating: " + wine.getRating());
    System.out.println("region: " + wine.getRegion());
    System.out.println("retailPrice: " + wine.getRetailPrice());
    System.out.println("varietal: " + wine.getVarietal());
    System.out.println("version: " + wine.getVersion());
    System.out.println("year: " + wine.getYear());
    System.out.println("supplierList: " + wine.getSupplierList());
  }
  

}
