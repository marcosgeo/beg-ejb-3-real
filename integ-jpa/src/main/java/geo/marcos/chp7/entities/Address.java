package geo.marcos.chp7.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

/**
 * To create ID generator table "ADDRESS_ID_GEN":
 *   create table "ADDRESS_ID_GEN" (
 *     "PRIMARY_KEY_NAME" varchar(4000) primary key,
 *     "NEXT_ID_VALUE" number(38)
 *   );
 *   
 * To initialize this table with data for this entity's ID generator
 * 'Address.'id' (starting with value '0'):
 */
@Entity
@NamedQueries({@NamedQuery(name="Address.findAll", query="select o from Address o")})
@TableGenerator(name="Address_ID_Generator", table="ADDRESS_ID_GEN",
    pkColumnName="PRIMARY_KEY_NAME", pkColumnValue="Address.id", valueColumnName="NEXT_ID_VALUE")
public class Address implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Column(nullable=false)
  @GeneratedValue(strategy=GenerationType.TABLE, generator="Address_ID_Generator")
  private Integer id;
  @Column(length=4000)
  private String city;
  private String state;
  @Column(length=4000)
  private String street1;
  private String street2;
  @Version
  private Integer version;
  private String zipCode;
  
  public Address() {}

  public Address(String city, String state, String street1, String street2, String zipCode) {
    setCity(city);
    setState(state);
    setStreet1(street1);
    setStreet2(street2);
    setZipCode(zipCode);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
  
  
}
