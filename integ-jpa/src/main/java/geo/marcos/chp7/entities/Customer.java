package geo.marcos.chp7.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name="Customer.findAll", query="select o from Customer o"),
  @NamedQuery(name="Customer.findByEmail", query="select o from Customer o where o.email = :email")
})
@Table(name="customer")
public class Customer extends BusinessContact {
  @Column(length=4000)
  private String email;

  @OneToMany(mappedBy="customer", cascade= {CascadeType.ALL}, orphanRemoval=true)
  private List<CartItem> cartItemList;
  
  @OneToMany(mappedBy="customer", cascade= {CascadeType.ALL}, orphanRemoval=true)
  private List<CustomerOrder> customerOrderList;
  
  @OneToMany(cascade= {CascadeType.ALL}, orphanRemoval=true)
  @JoinTable(name="customer_billing_address", 
    joinColumns=@JoinColumn(name="CUSTOMER_ID"), 
    inverseJoinColumns=@JoinColumn(name="ADDRESS_ID")
  )
  protected List<Address> billingAddressList;

  @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
  @JoinTable(name="customer_shipping_address",
    joinColumns = @JoinColumn(name="CUSTOMER_ID"),
    inverseJoinColumns=@JoinColumn(name="ADDRESS_ID")
  )
  private List<Address> shippingAddressList;
  
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name="DEFAULT_SHIPPING_ADDRESS")
  private Address defaultShippingAddress;
  
  @OneToOne(cascade= {CascadeType.ALL})
  @JoinColumn(name="DEFAULT_BILLING_ADDRESS")
  private Address defaultBillingAddress;
  
  public Customer() {}
  
  public Customer(String firstName, String lastName, String phone, String email,
      Address defaultShippingAddress, Address defaultBillingAddress) {
    super(firstName, lastName, phone);
    setEmail(email);
    setDefaultBillingAddress(defaultBillingAddress);
    setDefaultShippingAddress(defaultShippingAddress);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<CartItem> getCartItemList() {
    if (cartItemList == null) {
      cartItemList = new ArrayList<CartItem>();
    }
    return cartItemList;
  }

  public void setCartItemList(List<CartItem> cartItemList) {
    this.cartItemList = cartItemList;
  }
  
  public CartItem addCartItem(CartItem cartItem) {
    getCartItemList().add(cartItem);
    cartItem.setCustomer(this);
    return cartItem;
  }
  
  public CartItem removeCartItem(CartItem cartItem) {
    getCartItemList().remove(cartItem);
    cartItem.setCustomer(null);
    return cartItem;
  }

  public List<CustomerOrder> getCustomerOrderList() {
    if (customerOrderList == null) {
      customerOrderList = new ArrayList<CustomerOrder>();
    }
    return customerOrderList;
  }

  public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
    this.customerOrderList = customerOrderList;
  }
  
  public CustomerOrder addCustomerOrder(CustomerOrder customerOrder) {
    getCustomerOrderList().add(customerOrder);
    customerOrder.setCustomer(this);
    return customerOrder;
  }
  
  public CustomerOrder removeCustomerOrder(CustomerOrder customerOrder) {
    getCustomerOrderList().remove(customerOrder);
    customerOrder.setCustomer(null);
    return customerOrder;
  }

  public List<Address> getBillingAddressList() {
    if (billingAddressList == null) {
      billingAddressList = new ArrayList<Address>();
    }
    return billingAddressList;
  }

  public void setBillingAddressList(List<Address> billingAddressList) {
    this.billingAddressList = billingAddressList;
  }
  
  public Address addBillingAddress(Address billingAddress) {
    getBillingAddressList().add(billingAddress);
    return billingAddress;
  }

  public Address removeBillingAddress(Address billingAddress) {
    getBillingAddressList().remove(billingAddress);
    return billingAddress;
  }

  public List<Address> getShippingAddressList() {
    if (shippingAddressList == null) {
      shippingAddressList = new ArrayList<Address>();
    }
    return shippingAddressList;
  }

  public void setShippinAddressList(List<Address> shippinAddressList) {
    this.shippingAddressList = shippinAddressList;
  }

  public Address addShippingAddress(Address shippingAddress) {
    getShippingAddressList().add(shippingAddress);
    return shippingAddress;
  }

  public Address removeShippingAddress(Address shippingAddress) {
    getShippingAddressList().remove(shippingAddress);
    return shippingAddress;
  }

  public Address getDefaultShippingAddress() {
    return defaultShippingAddress;
  }
  
  public void setDefaultShippingAddress(Address address) {
    this.defaultShippingAddress = address;
  }
  
  public Address getDefaultBillingAddress() {
    return defaultBillingAddress;
  }
  
  public void setDefaultBillingAddress(Address address) {
    this.defaultBillingAddress = address;
  }
}
