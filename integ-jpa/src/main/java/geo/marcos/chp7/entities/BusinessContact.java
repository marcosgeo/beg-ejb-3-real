package geo.marcos.chp7.entities;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Access(AccessType.FIELD)
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
  @NamedQuery(name="BusinessContact.findAll", query="select o from BusinessContact o")
})
@Table(name="business_contact")
public class BusinessContact implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(nullable=false)
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name="FIRST_NAME", length=4000)
  private String firstName;
  
  @Column(name="LAST_NAME", length=4000)
  private String lastName;
  
  @Column(length=15)
  private String phone;
  
  @Version
  private Integer version;
  
  public BusinessContact() {}
  
  public BusinessContact(String firstName, String lastName, String phone) {
    setFirstName(firstName);
    setLastName(lastName);
    setPhone(phone);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
